package kr.chis.cismsaorder.order.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.chis.cismsaorder.order.domain.Order;
import kr.chis.cismsaorder.order.domain.OrderLineItemDto;
import kr.chis.cismsaorder.order.domain.OrderMapper;
import kr.chis.cismsaorder.order.domain.OrderRepository;
import kr.chis.cismsaorder.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.List;

import static org.springframework.web.reactive.function.server.ServerResponse.badRequest;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author InSeok
 * Date : 2020/08/11
 * Remark :
 */
@Component
@Slf4j
public class OrderlHandler {
    private final OrderService orderService;
    private final OrderRepository orderRepository;


    @Autowired
    public OrderlHandler(OrderService orderService, OrderRepository orderRepository) {
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
        log.info("오더저장 시작 api {}",request.path());
        return request.bodyToMono(OrderMapper.class)
                .map(v -> {
                    log.info("오더저장 검 api {}",request.path());
                    //int x = 1/0;
                    //if (1 == 1) throw new IllegalArgumentException("올바른 요청값이 아닙니다.");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    return v;

                })
                .flatMap(v -> ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(v), OrderMapper.class));
                //.onErrorReturn(ServerResponse.badRequest().build().block());


        //.doOnSuccess(v -> ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(v), OrderMapper.class))



    }
}
