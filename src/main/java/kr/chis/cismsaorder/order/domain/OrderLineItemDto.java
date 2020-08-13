package kr.chis.cismsaorder.order.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author InSeok
 * Date : 2020-07-29
 * Remark :
 */

@Data
@NoArgsConstructor
public class OrderLineItemDto implements Serializable {
    private String id;
    private String orderName;
    private String itemName;
    private Long orderQty;

    @QueryProjection
    public OrderLineItemDto(String id, String orderName, String itemName, Long orderQty) {
        this.id = id;
        this.orderName = orderName;
        this.itemName = itemName;
        this.orderQty = orderQty;
    }
}
