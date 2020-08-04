package kr.chis.cismsaorder.common;

import lombok.Getter;
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
 * Remark : 등록자,수정자를 관리하기위한 엔터티 베이스
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
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
