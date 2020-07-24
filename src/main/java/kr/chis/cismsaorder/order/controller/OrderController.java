package kr.chis.cismsaorder.order.controller;

import kr.chis.cismsaorder.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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


        return "/order/index";
    }
    @GetMapping("/cancel/{id}")
    public String orderCancel(@PathVariable Long id){

        return "/order/ordercancel";
    }
}
