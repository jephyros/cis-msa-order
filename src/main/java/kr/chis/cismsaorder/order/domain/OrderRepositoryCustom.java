package kr.chis.cismsaorder.order.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author InSeok
 * Date : 2020-07-29
 * Remark :
 */
public interface OrderRepositoryCustom {
    Page<OrderLineItemDto> findAllSearchString(String ordername, Pageable pageable);

    //findAllBySearchStrings(String userid, String evcode, String evname, Pageable pageable);
}
