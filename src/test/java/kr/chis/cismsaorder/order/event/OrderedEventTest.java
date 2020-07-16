package kr.chis.cismsaorder.order.event;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.order.repository.OrderRepository;
import kr.chis.cismsaorder.order.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */



@SpringBootTest
@ActiveProfiles("test")
//@Import(OrderedEventTestConfig.class)
class OrderedEventTest {

    @Autowired
    OrderService orderService;
    //OrderRepository orderRepository;

    @Test
    public void event_Publish_Test(){
        //given :
        Order order = Order.builder()
                .orderName("test1234")
                .orderStatus(OrderStatus.ORDER_PENGIND)
                .build();
        //when

        //orderRepository.save(order.savePublish());
        orderService.save(order);
        //then
        assertThat("d").isEqualTo("d");

    }

}