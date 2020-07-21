package kr.chis.cismsaorder;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.common.ShopStatus;
import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.order.domain.Order.OrderBuilder;
import kr.chis.cismsaorder.shop.domain.Shop;
import kr.chis.cismsaorder.shop.domain.Shop.ShopBuilder;

import java.time.LocalDateTime;

/**
 * @author InSeok
 * Date : 2020-07-21
 * Remark :
 */
public class TestDataProduct {

    public static ShopBuilder anShop(){
        return Shop.builder()
                .minOrderAmt(12000L)
                .shopStatus(ShopStatus.OPEN)
                .shopName("양꼬치대장");

    }


    public static OrderBuilder anOrder(){
        return Order.builder()
                .orderName("test")
                .shopId(anShop().build().getId())
                .orderStatus(OrderStatus.ORDER_PENGIND)
                .orderStatusTime(LocalDateTime.now())
                .orderAmoumt(13000L);

    }
}
