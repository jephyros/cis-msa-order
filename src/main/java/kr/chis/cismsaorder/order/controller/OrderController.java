package kr.chis.cismsaorder.order.controller;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("")
    public String orderIndex(){
        Order order = Order.builder()
                .orderName("test")
                .orderStatus(OrderStatus.ORDER_PENGIND)
                .orderStatusTime(LocalDateTime.now())
                .build();


        orderService.save(order);

        return "/order/index";
    }
    @GetMapping("/cancel")
    public String orderCancel(){
        Order order = Order.builder()
                .orderName("canceltest")
                .orderStatus(OrderStatus.ORDER_PENGIND)
                .orderStatusTime(LocalDateTime.now())
                .build();

        orderService.cancel(order);

        return "/order/ordercancel";
    }
}
