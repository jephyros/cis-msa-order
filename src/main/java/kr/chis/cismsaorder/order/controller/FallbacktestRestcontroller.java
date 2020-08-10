package kr.chis.cismsaorder.order.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author InSeok
 * Date : 2020-08-10
 * Remark :
 */
@RestController
public class FallbacktestRestcontroller {
    @GetMapping("/fallback2")
    public Mono<String> fallback2() {
        return Mono.just("order-fallback2-CIS");
    }
}
