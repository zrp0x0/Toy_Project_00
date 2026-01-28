package com.zrp.toyproject0.domain.item.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Item {
    
    // 아이디 / 이름 / 가격 / 수량 / 등록날짜
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long price;

    @Column
    private Integer count;

    @CreatedDate
    @Column(updatable = false) // 수정 시에는 날짜가 바뀌지 않도록 보호
    private LocalDateTime regDate;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    // 판매처 (회원가입 기능 완성 후)

}
