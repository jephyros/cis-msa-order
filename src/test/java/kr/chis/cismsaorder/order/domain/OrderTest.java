package kr.chis.cismsaorder.order.domain;

import kr.chis.cismsaorder.common.OrderStatus;
import org.junit.jupiter.api.Test;

import static kr.chis.cismsaorder.TestDataProduct.*;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author InSeok
 * Date : 2020-07-21
 * Remark :
 */
class OrderTest {


    @Test
    void 오더_승인_체크() {

        Order order = anOrder().orderAmoumt(11000L).build();
        order.ChangeOrderStatusAccept();
        assertThat(OrderStatus.ORDER_ACCEPT).describedAs("오더 상태값 [expect : ORDER_ACCEPT]").isEqualTo(order.getOrderStatus());

    }
    @Test
    void 오더_아이템추가_저장(){
        Order order = anOrder().orderAmoumt(11000L).build();
        OrderLineItem item2 = anOrderLineItem2().build();
        order.addItem(item2);

        assertThat(2).describedAs("오더아이템수 [expect:2]").isEqualTo(order.getOrderLineItems().size());


    }
}