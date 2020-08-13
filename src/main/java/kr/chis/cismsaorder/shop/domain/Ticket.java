package kr.chis.cismsaorder.shop.domain;

import kr.chis.cismsaorder.common.TicketStatus;
import kr.chis.cismsaorder.shop.event.TicketOpenedEvent;
import lombok.*;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author InSeok
 * Date : 2020/07/18
 * Remark :
 */
@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name="sp_ticket")
public class Ticket extends AbstractAggregateRoot<Ticket> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="st_id")
    private Long id;

    @Column(name="st_od_id")
    private String orderId;

    @Enumerated(EnumType.STRING)
    @Column(name="st_status")
    private TicketStatus ticketStatus;

    @Column(name="st_status_date")
    private LocalDateTime ticketStatusTime;

    public Ticket TicketOpenedPublish(){
        this.registerEvent(new TicketOpenedEvent(this));
        return this;
    }

}
