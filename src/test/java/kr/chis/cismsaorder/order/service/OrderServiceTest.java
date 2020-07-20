package kr.chis.cismsaorder.order.service;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.shop.domain.Shop;
import kr.chis.cismsaorder.shop.repository.ShopRepository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
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
    void 오더_정상등록() {
        //given


        Optional<Shop> save1 = shopRepository.findByShopName("양꼬치대장");

        Order order = new Order();
        order.setOrderName("testordername");
        order.setShopId(save1.get().getId());
        order.setOrderStatus(OrderStatus.ORDER_PENGIND);
        order.setOrderStatusTime(LocalDateTime.now());
        order.setOrderAmoumt(13000L);
        Order saveorder = orderService.save(order);
        assertThat(saveorder.getOrderName()).describedAs("오더 정상저장 [expect]:testordername").isEqualTo("testordername");


        //System.out.println("==============" + System.currentTimeMillis());

    }
    @Test//(expected = IllegalStateException.class)
    void 오더_최소금액_체크() {
        //given


        Optional<Shop> save1 = shopRepository.findByShopName("양꼬치대장");

        Order order = new Order();
        order.setOrderName("test");
        order.setShopId(save1.get().getId());
        order.setOrderStatus(OrderStatus.ORDER_PENGIND);
        order.setOrderStatusTime(LocalDateTime.now());
        order.setOrderAmoumt(11000L);

        //when then
        //최소금액 12000원인데 오더금액은 11000원 이기때문에 IllegalStateException이 발생되야한다.
        Assertions.assertThrows(IllegalStateException.class, ()-> orderService.save(order));

        //System.out.println("==============" + System.currentTimeMillis());

    }
}