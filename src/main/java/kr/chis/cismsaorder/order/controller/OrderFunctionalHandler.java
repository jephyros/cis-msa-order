package kr.chis.cismsaorder.order.controller;

import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.order.domain.OrderLineItemDto;
import kr.chis.cismsaorder.order.domain.OrderRepository;
import kr.chis.cismsaorder.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author InSeok
 * Date : 2020/08/11
 * Remark :
 */
@Component
@Slf4j
public class OrderFunctionalHandler {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderFunctionalHandler(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    public Mono<ServerResponse> orderList(ServerRequest request){
        Mono<List<OrderLineItemDto>> orderMono = Mono.fromCallable(
                    ()->orderService.findAllSearchString(request.queryParam("ordername").orElse(""))
                ).subscribeOn(Schedulers.elastic());
        return ok().body(orderMono,OrderLineItemDto.class);
    }

    public Mono<ServerResponse> orderList2(ServerRequest request){
        String page = request.queryParam("page").orElse("0");
        String size = request.queryParam("size").orElse("10");

        Mono<Page<Order>> list2 = Mono.just(orderRepository.findAll(PageRequest.of(Integer.valueOf(page), Integer.valueOf(size))));


        return ok().body(list2,Order.class);
    }
    public Mono<ServerResponse> orderCreate(ServerRequest request){
        log.info("======> {}",request.path());
        Mono.just("Str")
                .subscribe(System.out::println);

        Mono<String> mono = request.body(BodyExtractors.toMono(String.class))
                .map(v -> {
                    System.out.println("x : " + v);
                    return v;
                });
        //.subscribe(System.out::println);

        //Mono<String> mono = Mono.empty();
        return ok().body(mono,String.class);
        //Mono<Order> order = Mono.empty();
        //return ServerResponse.created(URI.create("/api/order")).build();
    }
}
