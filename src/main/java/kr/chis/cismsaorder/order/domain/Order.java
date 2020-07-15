package kr.chis.cismsaorder.order.domain;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.order.event.OrderSaveEvent;
import kr.chis.cismsaorder.order.repository.OrderRepository;
import lombok.*;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Column(name="od_status_data")
    private LocalDateTime orderStatusTime;


    @Column(name="insert_date")
    private LocalDateTime insertDateTime;

    @Column(name="insert_id")
    private String insert_id;

    public Order savePublish(){
        System.out.println("======"+ getId());
        this.registerEvent(new OrderSaveEvent(this));
        return this;
    }

}
