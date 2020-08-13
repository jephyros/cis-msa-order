package kr.chis.cismsaorder.order.domain;

import kr.chis.cismsaorder.common.BaseEntityWithAggreagteRoot;
import kr.chis.cismsaorder.common.OrderStatus;
import kr.chis.cismsaorder.order.event.OrderCanceledEvent;
import kr.chis.cismsaorder.order.event.OrderedEvent;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.AuditOverride;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;

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
@ToString//(callSuper = true)
@Table(name="od_order")
@Audited
@AuditOverride(forClass=BaseEntityWithAggreagteRoot.class)
public class Order extends BaseEntityWithAggreagteRoot {
    @Id @GeneratedValue(generator = "uuidGenerator")
    @GenericGenerator(name="uuidGenerator",strategy = "kr.chis.cismsaorder.common.IdGenerator")
    @Column(name="od_id")
    private String id;

    @Column(name="od_name")
    private String orderName;

    @Version
    @Column(name ="od_version")
    private Long version;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name ="oi_od_id")
    @NotAudited
    private List<OrderLineItem> orderLineItems = new ArrayList<>();

    @Column(name="od_shop_id")
    private Long shopId;

    @Enumerated(EnumType.STRING)
    @Column(name="od_status")
    private OrderStatus orderStatus;

    @Column(name="od_status_date")
    private LocalDateTime orderStatusTime;






    private Long orderAmoumt;

    public void addItem(OrderLineItem orderLineItem){
        this.orderLineItems.add(orderLineItem);
    }

    public Order(String orderName, List<OrderLineItem> orderLineItems, Long shopId, OrderStatus orderStatus, Long orderAmoumt) {
        this(null,orderName, orderLineItems, shopId, orderStatus, LocalDateTime.now(),  orderAmoumt);
    }

    public Order() {
    }

    @Builder
    public Order(String id,String orderName, List<OrderLineItem> orderLineItems, Long shopId, OrderStatus orderStatus, LocalDateTime orderStatusTime,  Long orderAmoumt) {
        this.id = id;
        this.orderName = orderName;
        this.orderLineItems.addAll(orderLineItems);
        this.shopId = shopId;
        this.orderStatus = orderStatus;
        this.orderStatusTime = orderStatusTime;
        this.orderAmoumt = orderAmoumt;
    }

    public Order acceptOrder(){
        this.orderStatus = OrderStatus.ORDER_ACCEPT;
        return this;
    }

    public Order updateOrder(String orderName){
        this.orderName = this.orderName + "/" +orderName;
        return this;
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
        if (this.orderStatus != OrderStatus.ORDER_PENDING){
            throw new IllegalStateException("오더 상태가 오더접수 가 아닙니다.");
                    //throw new IllegalStateException(String.format("최소 주문 금액은 %s 원 입니다.",shop.getMinOrderAmt()));
        }
        this.orderStatus = OrderStatus.ORDER_ACCEPT;
        this.orderStatusTime = LocalDateTime.now();
    }
    public void ChangeOrderStatusReject() {
        if (this.orderStatus != OrderStatus.ORDER_PENDING){
            throw new IllegalStateException("오더 상태가 오더접수 가 아닙니다.");
            //throw new IllegalStateException(String.format("최소 주문 금액은 %s 원 입니다.",shop.getMinOrderAmt()));
        }
        this.orderStatus = OrderStatus.ORDER_REJECT;
        this.orderStatusTime = LocalDateTime.now();
    }
}
