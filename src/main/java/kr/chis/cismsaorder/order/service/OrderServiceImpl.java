package kr.chis.cismsaorder.order.service;

import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.order.event.OrderSaveEvent;
import kr.chis.cismsaorder.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.stereotype.Service;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
@Service
public class OrderServiceImpl implements OrderService  {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order save(Order order) {
        Order saveOrder = orderRepository.save(order.savePublish());



        return saveOrder;
    }
}
