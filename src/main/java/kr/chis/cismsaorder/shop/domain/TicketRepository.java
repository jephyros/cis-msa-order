package kr.chis.cismsaorder.shop.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author InSeok
 * Date : 2020/07/18
 * Remark :
 */
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    public Optional<Ticket> findByOrderId(String orderId);
}
