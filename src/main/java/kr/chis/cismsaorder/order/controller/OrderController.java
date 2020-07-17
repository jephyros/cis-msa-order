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
        Order order = new Order();
        order.setOrderName("test");
        order.setOrderStatus(OrderStatus.ORDER_PENGIND);
        order.setOrderStatusTime(LocalDateTime.now());

        orderService.save(order);

        return "/order/index";
    }
    @GetMapping("/cancel")
    public String orderCancel(){
        Order order = new Order();
        order.setOrderName("cancelTest");
        order.setOrderStatus(OrderStatus.ORDER_PENGIND);
        order.setOrderStatusTime(LocalDateTime.now());

        orderService.cancel(order);

        return "/order/ordercancel";
    }
}