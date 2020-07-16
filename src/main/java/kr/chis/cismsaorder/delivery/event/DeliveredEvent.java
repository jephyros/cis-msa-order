package kr.chis.cismsaorder.delivery.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author InSeok
 * Date : 2020-07-16
 * Remark : 배달 완료 이벤트
 *
 */
public class DeliveredEvent extends ApplicationEvent {
    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public DeliveredEvent(Object source) {
        super(source);
    }
}
