package kr.chis.cismsaorder.order.domain;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQuery;
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
    public List<OrderLineItemDto> findAllSearchString(String ordername) {

        QOrder qOrder=QOrder.order;
        QOrderLineItem qOrderLineItem = QOrderLineItem.orderLineItem;


        JPQLQuery<OrderLineItemDto> query = from(qOrder)
                .join(qOrderLineItem).on(qOrder.id.eq(qOrderLineItem.orderId))
                .select(Projections.constructor(OrderLineItemDto.class,
                        qOrder.id,
                        qOrder.orderName,
                        qOrderLineItem.itemName,
                        qOrderLineItem.orderQty
                        ));

        if (ordername != null && !ordername.isEmpty()){
            query.where(qOrder.orderName.containsIgnoreCase(ordername));
        }
        //final List<OrderLineItemDto> orders = getQuerydsl().applyPagination(pageable,query).fetch();
        List<OrderLineItemDto> orders = query.fetch();
        return orders;
        //return new PageImpl<>(orders,pageable,query.fetchCount());

    }

    @Override
    public List<OrderLineItemDto> findAllSearchString2(String ordername) {

        QOrder qOrder=QOrder.order;
        QOrderLineItem qOrderLineItem = QOrderLineItem.orderLineItem;

        JPQLQuery<OrderLineItemDto> query = from(qOrder)
                .innerJoin(qOrderLineItem).on(qOrder.id.eq(qOrderLineItem.orderId))
                .select(
                        new QOrderLineItemDto(qOrder.id, qOrder.orderName, qOrderLineItem.itemName, qOrderLineItem.orderQty)
                );

        if (ordername != null && !ordername.isEmpty()){
            query.where(qOrder.orderName.containsIgnoreCase(ordername));
        }
//        //final List<OrderLineItemDto> orders = getQuerydsl().applyPagination(pageable,query).fetch();
        List<OrderLineItemDto> orders = query.fetch();
        return orders;
        //return new PageImpl<>(orders,pageable,query.fetchCount());

    }
}
