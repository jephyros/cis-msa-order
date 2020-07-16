package kr.chis.cismsaorder.order.event;

import kr.chis.cismsaorder.delivery.event.DeliveryEventListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author InSeok
 * Date : 2020-07-16
 * Remark :
 */
//@Configuration
public class OrderedEventTestConfig {
    //@Bean
    public DeliveryEventListener orderedEventListener(){
        return new DeliveryEventListener();
    }
}
