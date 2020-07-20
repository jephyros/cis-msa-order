package kr.chis.cismsaorder.order.domain;

import lombok.*;

import javax.persistence.*;

/**
 * @author InSeok
 * Date : 2020-07-20
 * Remark :
 */
@Entity
@Getter
@Table(name="od_order_item")
public class OrderLineItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="oi_id")
    private Long id;


    @Column(name="oi_name")
    private String orderName;

    @Column(name="oi_qty")
    private Long orderQty;

    @Column(name="oi_price")
    private Long orderPrice;

    @Column(name="oi_amt")
    private Long orderAmount;


}
