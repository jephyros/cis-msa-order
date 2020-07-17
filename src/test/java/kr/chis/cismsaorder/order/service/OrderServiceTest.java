package kr.chis.cismsaorder.order.service;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.order.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author InSeok
 * Date : 2020/07/18
 * Remark :
 */
@SpringBootTest
@ActiveProfiles("test")
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    void save() {
        Order order = new Order();
        order.setOrderName("test");
        order.setOrderStatus(OrderStatus.ORDER_PENGIND);
        order.setOrderStatusTime(LocalDateTime.now());
        orderService.save(order);
        assertThat("d").describedAs("오더 저장 [expect:d]").isEqualTo("d");
    }
}