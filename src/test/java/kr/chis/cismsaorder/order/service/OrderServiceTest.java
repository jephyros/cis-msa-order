package kr.chis.cismsaorder.order.service;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.common.ShopStatus;
import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.shop.domain.Shop;
import kr.chis.cismsaorder.shop.repository.ShopRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Autowired
    ShopRepository shopRepository;


    @Test
    void save() {
        //given


        Optional<Shop> save1 = shopRepository.findByShopName("양꼬치대장");

        Order order = new Order();
        order.setOrderName("test");
        order.setShopId(save1.get().getId());
        order.setOrderStatus(OrderStatus.ORDER_PENGIND);
        order.setOrderStatusTime(LocalDateTime.now());
        order.setOrderAmoumt(13000L);
        orderService.save(order);
        assertThat("d").describedAs("오더 저장 [expect:d]").isEqualTo("d");
        //System.out.println("==============" + System.currentTimeMillis());
    }
}