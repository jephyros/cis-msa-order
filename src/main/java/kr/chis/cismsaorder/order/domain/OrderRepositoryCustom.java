package kr.chis.cismsaorder.order.domain;

import java.util.List;

/**
 * @author InSeok
 * Date : 2020-07-29
 * Remark :
 */
public interface OrderRepositoryCustom {
    List<OrderLineItemDto> findAllSearchString(String ordername);

    //findAllBySearchStrings(String userid, String evcode, String evname, Pageable pageable);
}
