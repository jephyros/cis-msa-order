package kr.chis.cismsaorder.order.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author InSeok
 * Date : 2020-07-29
 * Remark :
 */

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemDto {
    private Long id;
    private String orderName;
    private String itemName;
    private Long orderQty;
}
