package kr.chis.cismsaorder.order.service;

import kr.chis.cismsaorder.order.domain.Order;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
public interface OrderService {
    public Order save(Order order);
    public Order cancel(Order order);

    public Order orderAccept(Long orderId);
}
