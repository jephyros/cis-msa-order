package kr.chis.cismsaorder.common;

import lombok.Getter;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**
 * @author InSeok
 * Date : 2020-08-04
 * Remark :
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Audited
public class BaseEntityWithoutAggreagteRoot {
    @CreatedDate
    @Column(name="create_date")
    private LocalDateTime createDate;

    @CreatedBy
    @Column(name="create_id")
    private String createId;

    @LastModifiedDate
    @Column(name="modify_date")
    private LocalDateTime modifyData;

    @LastModifiedBy
    @Column(name="modify_id")
    private String modifyId;
}
