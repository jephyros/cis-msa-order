package kr.chis.cismsaorder.order.controller;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.order.domain.OrderLineItem;
import kr.chis.cismsaorder.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
@Controller
@Slf4j
@RequestMapping("/order")
public class OrderController {


    @Autowired
    OrderService orderService;

    @GetMapping("")
    public String orderIndex(){

        OrderLineItem item1 = OrderLineItem.builder()
                .itemName("양갈비꼬치")
                .orderQty(1L)
                .orderPrice(1500L)
                .orderAmount(15000L)
                .build();
        OrderLineItem item2 = OrderLineItem.builder()
                .itemName("양꼬치")
                .orderQty(1L)
                .orderPrice(1200L)
                .orderAmount(12000L)
                .build();
        Order order = Order.builder()
                .orderName("양꼬치주문1번")
                .shopId(1L)
                .orderLineItems(Arrays.asList(item1,item2))
                .orderStatus(OrderStatus.ORDER_PENGIND)
                .orderStatusTime(LocalDateTime.now())
                .orderAmoumt(13000L).build();


        orderService.save(order);

        return "/order/index";
    }
    @GetMapping("/cancel/{id}")
    public String orderCancel(@PathVariable Long id){

        return "/order/ordercancel";
    }
}
