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
    private final ObjectMapper mapper;

    @Autowired
    public OrderlHandler(OrderService orderService, OrderRepository orderRepository, ObjectMapper mapper) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
        this.mapper = mapper;
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

        Mono<OrderMapper> mono = request.body(BodyExtractors.toMono(String.class))
                .map(v -> {
                            try {
                                return mapper.readValue(v, OrderMapper.class);
                            } catch (JsonProcessingException e) {
                                e.printStackTrace();
                                return null;
                            }
                        }
                );


        //Mono<String> mono = Mono.empty();
        return ok().contentType(MediaType.APPLICATION_JSON).body(mono,OrderMapper.class);

    }
}
