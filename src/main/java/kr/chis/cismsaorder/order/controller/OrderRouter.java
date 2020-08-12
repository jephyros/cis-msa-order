package kr.chis.cismsaorder.order.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author InSeok
 * Date : 2020/08/11
 * Remark :
 */
@Configuration
@EnableWebFlux
public class OrderRouter {
    @Bean
    public RouterFunction<ServerResponse> routes(OrderlHandler handler){

        return nest(path("/api/order"),
                route(GET("/list"),handler::orderList)
                .andRoute(GET("/list2"),handler::orderList2)
                .andRoute(POST("/"),handler::orderCreate)
        );

    }
    /*
    resilience4j
     */
}
