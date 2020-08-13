package kr.chis.cismsaorder.shop.service;

import kr.chis.cismsaorder.common.TicketStatus;
import kr.chis.cismsaorder.shop.domain.Ticket;
import kr.chis.cismsaorder.shop.domain.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

/**
 * @author InSeok
 * Date : 2020/07/18
 * Remark :
 */
@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;

    @Autowired
    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    @Transactional
    public Ticket create(String orderId) {
        if(!ticketRepository.findByOrderId(orderId).isPresent()){
            Ticket ticket = Ticket.builder()
                    .orderId(orderId)
                    .ticketStatus(TicketStatus.TICKET_OPEN)
                    .ticketStatusTime(LocalDateTime.now())
                    .build();
            return ticketRepository.save(ticket.TicketOpenedPublish());
        }
        return null;
    }
}
