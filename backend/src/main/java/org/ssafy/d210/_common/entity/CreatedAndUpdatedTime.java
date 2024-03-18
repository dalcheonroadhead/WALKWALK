package org.ssafy.d210._common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CreatedAndUpdatedTime {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

}

/*
* MappedSuperClass:
*  상속관계 매핑 어노테이션이다.
*  부모 클래스는 따로 테이블을 매핑하지 않고, 부모클래스의 컬럼만 빼와서 자식클래스의 테이블 매핑 시,
*  부모 클래스 컬럼을 추가 매핑 시켜준다.
*
*  EntityListeners:
*   Entity 관련 이벤트 동작 몇 가지를 관찰하고 있다가, 특정 이벤트 발생 시 어떠한 역할을 하는 어노테이션
*   여기선 (AuditingEntityListener.class)라는 말이 앞에 붙어있는데, 해당 클래스를 자동으로 값을 넣어주는 기능인
*   Auditing Event의 대상 클래스로 사용하겠다는 뜻이다. 그렇다면 이것은 InsertEvent 를 Listening 하고 있는 EntityListener 이다.
* */
