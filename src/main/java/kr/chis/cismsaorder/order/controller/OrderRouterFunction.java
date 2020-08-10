package kr.chis.cismsaorder.order.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;

/**
 * @author InSeok
 * Date : 2020/08/11
 * Remark :
 */
@Configuration
@EnableWebFlux
public class OrderRouterFunction {
    @Bean
    public RouterFunction<ServerResponse> routes(OrderFunctionalHandler handler){
        return RouterFunctions.route(GET("/api/order/list"),handler::orderList);
    }
    /*
    resilience4j
     */
}
