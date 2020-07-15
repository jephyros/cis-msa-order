package kr.chis.cismsaorder.order.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
@Controller
@RequestMapping("/order")
public class OrderController {
    @GetMapping("")
    public String orderIndex(){
        return "/order/index";
    }
}
