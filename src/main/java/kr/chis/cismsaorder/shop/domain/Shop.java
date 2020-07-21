package kr.chis.cismsaorder.shop.domain;

import kr.chis.cismsaorder.common.ShopStatus;
import kr.chis.cismsaorder.common.TicketStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author InSeok
 * Date : 2020/07/18
 * Remark :
 */

@Entity
@Getter
@Setter
@Table(name="sp_shop")
public class Shop {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sp_id")
    private Long id;

    @Column(name="sp_name")
    private String shopName;

    @Enumerated(EnumType.STRING)
    @Column(name="sp_status")
    private ShopStatus shopStatus;

    @Column(name="sp_min_order_amt")
    private Long minOrderAmt;


    public Shop() {
    }

    @Builder
    public Shop(String shopName, ShopStatus shopStatus, Long minOrderAmt) {
        this.shopName = shopName;
        this.shopStatus = shopStatus;
        this.minOrderAmt = minOrderAmt;
    }


    public boolean isOpen() {
        if ( this.shopStatus == ShopStatus.OPEN){
            return true;
        }
        return false;
    }

    public boolean isValidOrderAmount(Long totalAmount) {
        if (totalAmount >= this.minOrderAmt){
            return true;
        }
        return false;
    }
}
