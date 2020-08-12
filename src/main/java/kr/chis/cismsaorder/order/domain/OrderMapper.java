package kr.chis.cismsaorder.order.domain;


import lombok.NoArgsConstructor;

/**
 * @author InSeok
 * Date : 2020-08-12
 * Remark :
 */

public class OrderMapper {
    private String orderName;
    private Long shopId;

    public OrderMapper() {
    }

    public OrderMapper(String orderName, Long shopId) {
        this.orderName = orderName;
        this.shopId = shopId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    @Override
    public String toString() {
        return "OrderMapper{" +
                "orderName='" + orderName + '\'' +
                ", shopId=" + shopId +
                '}';
    }
}
