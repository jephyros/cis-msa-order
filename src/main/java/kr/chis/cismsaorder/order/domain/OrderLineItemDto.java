package kr.chis.cismsaorder.order.domain;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author InSeok
 * Date : 2020-07-29
 * Remark :
 */

@Data
@NoArgsConstructor
public class OrderLineItemDto {
    private Long id;
    private String orderName;
    private String itemName;
    private Long orderQty;

    @QueryProjection
    public OrderLineItemDto(Long id, String orderName, String itemName, Long orderQty) {
        this.id = id;
        this.orderName = orderName;
        this.itemName = itemName;
        this.orderQty = orderQty;
    }
}
