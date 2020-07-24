package kr.chis.cismsaorder.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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

    WebClient client = WebClient.create();

    @PostMapping("reg")
    public ResponseEntity orderSave(){
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
