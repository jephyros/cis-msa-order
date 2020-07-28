package kr.chis.cismsaorder.order.service;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.order.domain.OrderRepository;
import kr.chis.cismsaorder.order.domain.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
@Service
public class OrderServiceImpl implements OrderService  {
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private final KafkaTemplate<String ,String> kafkaTemplate;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderValidator orderValidator, KafkaTemplate<String, String> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public Order save(Order order) {

        order.validate(orderValidator);
        Order saveOrder = orderRepository.save(order.savePublish());

        kafkaTemplate.send("msaorder",saveOrder.toString());

        return saveOrder;
    }

    @Override
    public Order cancel(Order order) {
        Order saveOrder = orderRepository.save(order.cancelPublish());

        return saveOrder;
    }


    @Override
    @Transactional
    public Order orderAccept(Long orderId) {
        return orderRepository.findById(orderId)
                .map((order)-> {
                    order.ChangeOrderStatusAccept();
                    return order;
                }).orElseThrow(()-> new NoSuchElementException("수정하려는 오더가없음니다."));


    }

    @Override
    public void del(Long orderid) {
        orderRepository.findById(orderid).ifPresentOrElse(
             (order)-> orderRepository.delete(order)
            ,()-> {
                 throw new NoSuchElementException("삭제하고자하는 데이터가 없습니다.");
                }
            );
    }
}
