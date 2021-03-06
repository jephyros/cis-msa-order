package kr.chis.cismsaorder.threadtest;

import kr.chis.cismsaorder.order.domain.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author InSeok
 * Date : 2020-07-24
 * Remark :
 */
@Slf4j
public class LoadTest {
    static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException, BrokenBarrierException {

        ExecutorService es = Executors.newFixedThreadPool(100);

        RestTemplate rt = new RestTemplate();
        //String url = "http://localhost:8180/api/order";
        String url = "http://localhost:8181/api/v1/shops";


        CyclicBarrier barrier = new CyclicBarrier(101);

        for (int i = 0; i < 100; i++) {
            es.submit(() -> {

                StopWatch sw = new StopWatch();
                int idx = counter.addAndGet(1);
                barrier.await();

                log.info("Thread {}", idx);
                sw.start();
                String result = rt.getForObject(url, String.class);

                //OrderMapper reqParam = new OrderMapper("오더"+ idx,1L);
                //OrderMapper result = rt.postForObject(url, reqParam, OrderMapper.class);

                sw.stop();
                log.info("Elasped {} : {} - result : {}", idx, sw.getTotalTimeSeconds(), result.toString());
                return null;
            });
        }

        barrier.await();
        StopWatch main = new StopWatch();
        main.start();

        es.shutdown();
        es.awaitTermination(100, TimeUnit.SECONDS);

        main.stop();

        log.info("Total time : {}", main.getTotalTimeSeconds());
    }
}
