package kr.chis.cismsaorder.order.domain;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.order.event.OrderCanceledEvent;
import kr.chis.cismsaorder.order.event.OrderedEvent;
import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="od_order")
public class Order extends AbstractAggregateRoot<Order> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="od_id")
    private Long id;


    @Column(name="od_name")
    private String orderName;

    @Enumerated(EnumType.STRING)
    @Column(name="od_status")
    private OrderStatus orderStatus;

    @Column(name="od_status_date")
    private LocalDateTime orderStatusTime;


    @Column(name="insert_date")
    private LocalDateTime insertDateTime;

    @Column(name="insert_id")
    private String insert_id;

    public Order savePublish(){
        this.registerEvent(new OrderedEvent(this));
        return this;
    }

    public Order cancelPublish() {
        this.registerEvent(new OrderCanceledEvent(this));
        return this;
    }

    public Long totalAmount() {
        return 10000L;
    }
}
