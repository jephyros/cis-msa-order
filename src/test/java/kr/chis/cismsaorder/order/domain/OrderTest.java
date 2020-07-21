package kr.chis.cismsaorder.order.domain;

import kr.chis.cismsaorder.common.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static kr.chis.cismsaorder.TestDataProduct.anOrder;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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
}