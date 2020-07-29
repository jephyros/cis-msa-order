package kr.chis.cismsaorder.order.service;

import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.order.domain.OrderLineItemDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
public interface OrderService {
    Order save(Order order);
    Order cancel(Order order);

    Order orderAccept(Long orderId);

    void del(Long orderid);

    List<OrderLineItemDto> findAllSearchString(String ordername);
    List<Order> findAllOrder();
}
