package kr.chis.cismsaorder.order.repository;

import kr.chis.cismsaorder.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author InSeok
 * Date : 2020-07-15
 * Remark :
 */
public interface OrderRepository extends JpaRepository<Order,Long> {

}
