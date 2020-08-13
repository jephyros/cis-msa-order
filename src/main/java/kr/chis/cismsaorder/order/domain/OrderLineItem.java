package kr.chis.cismsaorder.order.domain;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @author InSeok
 * Date : 2020-07-20
 * Remark :
 */
@Entity
@Getter
@ToString(exclude = "orderId")
@Table(name="od_order_item")
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="oi_id")
    private Long id;

    //@ManyToOne
    //@JoinColumn(name = "delivery_id", nullable = false, updatable = false)
//    @ManyToOne
//    @JoinColumn(name ="oi_od_id",nullable = false,updatable = false)
//    private Order order;

    @Column(name="oi_od_id")
    private String orderId;

    @Column(name="oi_name")
    private String itemName;

    @Column(name="oi_qty")
    private Long orderQty;

    @Column(name="oi_price")
    private Long orderPrice;

    @Column(name="oi_amt")
    private Long orderAmount;


    @Builder
    public OrderLineItem(Long id,String itemName, Long orderQty, Long orderPrice, Long orderAmount) {
        this.id = id;
        this.itemName = itemName;
        this.orderQty = orderQty;
        this.orderPrice = orderPrice;
        this.orderAmount = orderAmount;
    }
//    public void setOrder(Order order){
//        this.order = order;
//    }

    public OrderLineItem() {
    }
}
