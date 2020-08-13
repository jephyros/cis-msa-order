package kr.chis.cismsaorder.order.domain;

import kr.chis.cismsaorder.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
public interface OrderRepository extends JpaRepository<Order,String> {

}
