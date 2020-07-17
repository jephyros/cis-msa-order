package kr.chis.cismsaorder.shop.service;

import kr.chis.cismsaorder.shop.domain.Ticket;

/**
 * @author InSeok
 * Date : 2020/07/18
 * Remark :
 */
public interface TicketService {
    public Ticket create(Long orderId);
}
