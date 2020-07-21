package kr.chis.cismsaorder.order.domain;

import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.order.event.OrderCanceledEvent;
import kr.chis.cismsaorder.order.event.OrderedEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
@Entity
@Getter
@Table(name="od_order")
public class Order extends AbstractAggregateRoot<Order> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="od_id")
    private Long id;

    @Column(name="od_name")
    private String orderName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "od_id")
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    @Column(name="od_shop_id")
    private Long shopId;

    @Enumerated(EnumType.STRING)
    @Column(name="od_status")
    private OrderStatus orderStatus;

    @Column(name="od_status_date")
    private LocalDateTime orderStatusTime;


    @Column(name="insert_date")
    private LocalDateTime insertDateTime;

    @Column(name="insert_id")
    private String insert_id;

    private Long orderAmoumt;

    public Order(String orderName, List<OrderLineItem> orderLineItems, Long shopId, OrderStatus orderStatus, Long orderAmoumt) {
        this(orderName, orderLineItems, shopId, orderStatus, LocalDateTime.now(), LocalDateTime.now(), null, orderAmoumt);
    }


    @Builder
    public Order(String orderName, List<OrderLineItem> orderLineItems, Long shopId, OrderStatus orderStatus, LocalDateTime orderStatusTime, LocalDateTime insertDateTime, String insert_id, Long orderAmoumt) {
        this.orderName = orderName;
        this.orderLineItems = orderLineItems;
        this.shopId = shopId;
        this.orderStatus = orderStatus;
        this.orderStatusTime = orderStatusTime;
        this.insertDateTime = insertDateTime;
        this.insert_id = insert_id;
        this.orderAmoumt = orderAmoumt;
    }



    public Order savePublish(){
        this.registerEvent(new OrderedEvent(this));
        return this;
    }

    public Order cancelPublish() {
        this.registerEvent(new OrderCanceledEvent(this));
        return this;
    }

    public Long totalAmount() {
        return orderAmoumt;
    }
    public void validate(OrderValidator orderValidator){
        orderValidator.validate(this);
    }
    public void ChangeOrderStatusAccept() {
        this.orderStatus = OrderStatus.ORDER_ACCEPT;
        this.orderStatusTime = LocalDateTime.now();
    }
}
