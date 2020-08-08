package kr.chis.cismsaorder.order.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.chis.cismsaorder.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author InSeok
 * Date : 2020-07-16
 * Remark : 주문서비스로 수신되는 이벤트 리스터
 */
@Slf4j
@Component
public class OrderEventListener {
    private final OrderService orderService;
    private final ObjectMapper mapper;

    public OrderEventListener(OrderService orderService, ObjectMapper mapper) {
        this.orderService = orderService;
        this.mapper = mapper;
    }

    //
    @KafkaListener(topics = "Shop-isValid")
    public void shopIsValidEventListener(ConsumerRecord consumerRecord) throws JsonProcessingException {

        //        Order order = mapper.readValue(consumerRecord.value().toString(), Order.class);
        OrderAcceptMessage orderAcceptMessage = mapper.readValue(consumerRecord.value().toString(), OrderAcceptMessage.class);
        if(orderAcceptMessage.isSuccess()){
            orderService.accept(orderAcceptMessage.getOrderId());
            log.info("OrderAccept(shopIsValid Event Listener) orderId :'{}'",orderAcceptMessage.getOrderId());
        }else{
            orderService.reject(orderAcceptMessage.getOrderId());
            log.info("OrderReject(shopIsValid Event Listener) orderId :'{}'",orderAcceptMessage.getOrderId());

        }
    }



//
//    //샵에서 티켓 오픈되는 이벤트수신하여 오더 상태변경 (OrderStatus.ORDER_PENGIND ->ORDER_ACCEPT )
//    @EventListener
//    public void ticketOpenedHandle(TicketOpenedEvent event){
//        log.info("샵 티켓 오픈 이벤트 수신 :'" + event.getOrderId()+ "'");
//        orderService.orderAccept(event.getOrderId());
//    }
}
