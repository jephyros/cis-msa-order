package kr.chis.cismsaorder.order.event;

import kr.chis.cismsaorder.shop.event.ShopEventListener;

/**
 * @author InSeok
 * Date : 2020-07-16
 * Remark :
 */
//@Configuration
public class OrderedEventTestConfig {
    //@Bean
    public ShopEventListener orderedEventListener(){
        return new ShopEventListener(ticketService);
    }
}
