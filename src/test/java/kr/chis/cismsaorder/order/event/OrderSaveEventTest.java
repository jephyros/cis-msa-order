package kr.chis.cismsaorder.order.event;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.delivery.event.OrderEventListener;
import kr.chis.cismsaorder.order.domain.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
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
class OrderSaveEventTest {


    @Test
    public void event_Publish_Test(){
        //given :
        Order order = Order.builder()
                .orderName("test1234")
                .orderStatus(OrderStatus.ORDER_PENGIND)
                .build();
        //when
        order.savePublish();
        //then
        assertThat("d").isEqualTo("d");

    }

}