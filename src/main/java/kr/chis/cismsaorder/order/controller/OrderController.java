package kr.chis.cismsaorder.order.controller;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
@Controller
@Slf4j
@RequestMapping("/order")
public class OrderController {
    private final String URL1 = "http://192.168.0.137:8081/service1?req={req}";
    private final String URL2 = "http://192.168.0.137:8081/service2?req={req}";

    WebClient client = WebClient.create();

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
    @GetMapping("/resttest")
    public Mono<String> rest(int idx) {


        log.info("========Rest Called ==========");
        return client.get().uri(URL1, idx).exchange()
                .flatMap(c -> c.bodyToMono(String.class))
                .flatMap(s->client.get().uri(URL2,s).exchange())
                .flatMap(c->c.bodyToMono(String.class)).log();


    }
}
