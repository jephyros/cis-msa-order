package kr.chis.cismsaorder.order.service;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.order.domain.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public Order save(Order order) {

        if (!isValidOrderAmount(order.totalAmount())) {
            throw new IllegalStateException(String.format("최소 주문 금액은  %s 원 이상 입니다.", "12,000"));
        }

        Order saveOrder = orderRepository.save(order.savePublish());

        return saveOrder;
    }

    @Override
    public Order cancel(Order order) {
        Order saveOrder = orderRepository.save(order.cancelPublish());

        return saveOrder;
    }

    private boolean isValidOrderAmount(Long totalAmount) {
        if (totalAmount < 12000) {
            return false;
        }
        return true;

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
