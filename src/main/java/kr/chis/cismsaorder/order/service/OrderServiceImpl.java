package kr.chis.cismsaorder.order.service;

import kr.chis.cismsaorder.order.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService  {
    private final OrderRepository orderRepository;
    private final OrderValidator orderValidator;
    private final OrderRepositoryCustom orderRepositoryCustom;
    private final KafkaTemplate<String ,Object> kafkaTemplate;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderValidator orderValidator, OrderRepositoryCustom orderRepositoryCustom, KafkaTemplate<String, Object> kafkaTemplate) {
        this.orderRepository = orderRepository;
        this.orderValidator = orderValidator;
        this.orderRepositoryCustom = orderRepositoryCustom;

        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    @Transactional
    public Order save(Order order) {

        order.validate(orderValidator);
        Order saveOrder = orderRepository.save(order.savePublish());

        //JsonSerializer<Order> tJsonSerializer = new JsonSerializer<>(saveOrder);
        kafkaTemplate.send("Order-ordered",saveOrder);

        return saveOrder;
    }

    @Override
    public Order cancel(Order order) {
        Order saveOrder = orderRepository.save(order.cancelPublish());

        return saveOrder;
    }


    @Override
    @Transactional
    public Order accept(Long orderId) {
        return orderRepository.findById(orderId)
                .map((order)-> {
                    order.ChangeOrderStatusAccept();
                    return order;
                }).orElseThrow(()-> new NoSuchElementException("확정하려는 오더가없음니다."));


    }
    @Override
    @Transactional
    public Order reject(Long orderId) {
        return orderRepository.findById(orderId)
                .map((order)-> {
                    order.ChangeOrderStatusReject();
                    return order;
                }).orElseThrow(()-> new NoSuchElementException("거절하려는 오더가없음니다."));
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

    @Override
    public List<OrderLineItemDto> findAllSearchString(String ordername) {
        return orderRepositoryCustom.findAllSearchString2(ordername);
    }

    @Override
    public List<Order> findAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public void update(Long orderId) {
        orderRepository.findById(orderId).ifPresentOrElse(
                (order)->{
                    order.updateOrder("오더명변경");
                    //log.info(" ======== order name : {}" ,order.getOrderName());
                },
                ()->{
                    throw new NoSuchElementException("수정하고자하는 데이터가 없습니다.");
                }
        );
    }

}
