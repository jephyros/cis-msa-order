package kr.chis.cismsaorder.order.controller;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.order.domain.OrderLineItem;
import kr.chis.cismsaorder.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */

@RestController
@Slf4j
@RequestMapping("/api/order")
public class OrderRestController {
    private final String URL1 = "http://localhost:8081/api/v1/shops/service1?req={req}";
    private final String URL2 = "http://localhost:8081/api/v1/shops/service2?req={req}";

//    private final String URL1 = "http://localhost:8081/service1?req={req}";
//    private final String URL2 = "http://localhost:8081/service2?req={req}";

    private final OrderService orderService;
    WebClient client = WebClient.create();

    @Autowired
    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("reg")
    public ResponseEntity orderSave(){
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
        return null;
    }
    @PostMapping("del")
    public ResponseEntity orderDel(@RequestParam(value="orderid", defaultValue="0") String orderid){


        orderService.del(Long.valueOf(orderid));
        return null;
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
