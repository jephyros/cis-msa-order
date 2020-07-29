package kr.chis.cismsaorder.order.controller;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.order.domain.OrderLineItem;
import kr.chis.cismsaorder.order.domain.OrderLineItemDto;
import kr.chis.cismsaorder.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.NoSuchElementException;

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
    @DeleteMapping("{id}")
    public ResponseEntity orderDel(@PathVariable("id") Long orderid){

        log.info("======== order id : {}",orderid);

        try {
            orderService.del(orderid);
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
    @GetMapping("list")
    public Page<OrderLineItemDto> orderList(@RequestParam(value="ordername", defaultValue="") String ordername
                                            , @PageableDefault(size = 10, direction = Sort.Direction.DESC, sort = "id") Pageable pageable){

        return orderService.findAllSearchString(ordername, pageable);
    }
    @GetMapping("list2")
    public Page<Order> orderList2(
             @PageableDefault(size = 10, direction = Sort.Direction.DESC, sort = "id") Pageable pageable){

        return orderService.findAllOrder(pageable);
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
