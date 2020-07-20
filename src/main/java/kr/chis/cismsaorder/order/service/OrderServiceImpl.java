package kr.chis.cismsaorder.order.service;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.order.domain.OrderRepository;
import kr.chis.cismsaorder.order.domain.OrderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()){
            optionalOrder.get().setOrderStatus(OrderStatus.ORDER_ACCEPT);
            return optionalOrder.get();
        }
        throw new RuntimeException("수정하려는오더가없음");
    }
}
