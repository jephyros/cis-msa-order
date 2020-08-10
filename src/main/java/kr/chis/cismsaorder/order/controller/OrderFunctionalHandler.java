package kr.chis.cismsaorder.order.controller;

import kr.chis.cismsaorder.order.domain.OrderLineItemDto;
import kr.chis.cismsaorder.order.service.OrderService;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author InSeok
 * Date : 2020/08/11
 * Remark :
 */
@Component
public class OrderFunctionalHandler {
    private final OrderService orderService;

    public OrderFunctionalHandler(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("list")
    public Mono<ServerResponse> orderList(ServerRequest request){
        Mono<List<OrderLineItemDto>> orderMono = Mono.just(orderService.findAllSearchString(request.queryParam("ordername").orElse("")));
        return ServerResponse.ok().body(orderMono,OrderLineItemDto.class);
    }
}
