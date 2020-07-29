package kr.chis.cismsaorder.order.domain;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author InSeok
 * Date : 2020-07-29
 * Remark :
 */
@Repository
public class OrderRepositoryCustomImpl extends QuerydslRepositorySupport implements OrderRepositoryCustom{

    public OrderRepositoryCustomImpl() {
        super(Order.class);
    }

    @Override
    public Page<OrderLineItemDto> findAllSearchString(String ordername, Pageable pageable) {

        QOrder qOrder=QOrder.order;
        QOrderLineItem qOrderLineItem = QOrderLineItem.orderLineItem;
        JPQLQuery<OrderLineItemDto> query = from(qOrderLineItem)
                //.innerJoin(qOrder.orderLineItems)
                .select(Projections.constructor(OrderLineItemDto.class,
//                        qOrder.id,
//                        qOrder.orderName,
                        qOrderLineItem.id,
                        qOrderLineItem.itemName,
                        qOrderLineItem.itemName,
                        qOrderLineItem.orderQty
                        ));

        if (ordername != null && !ordername.isEmpty()){
            query.where(qOrder.orderName.containsIgnoreCase(ordername));
        }
        final List<OrderLineItemDto> orders = getQuerydsl().applyPagination(pageable,query).fetch();
        return new PageImpl<>(orders,pageable,query.fetchCount());

    }
}
