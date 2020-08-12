package kr.chis.cismsaorder.threadtest;

import kr.chis.cismsaorder.order.domain.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author InSeok
 * Date : 2020-08-12
 * Remark :
 */
@Slf4j
public class Loadtest2 {
    static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        RestTemplate rt = new RestTemplate();
        String url = "http://localhost:8180/api/order";
        //String url = "http://aci.broadwave.co.kr";
        OrderMapper reqParam = new OrderMapper("오더1", 1L);


        OrderMapper orderMapper = rt.postForObject(url, reqParam, OrderMapper.class);

        // ResponseEntity<String> res = rt.exchange(url, HttpMethod.POST, entity, String.class);
        log.info("test====================> {} ", orderMapper.toString());
    }
}
