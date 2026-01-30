package com.zrp.toyproject0.domain.item.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import com.zrp.toyproject0.domain.comment.entity.Comment;
import com.zrp.toyproject0.domain.member.entity.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(exclude = {"member", "comments"})
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(indexes = @Index(columnList = "name", name = "indexName1"))
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    Member member;

    // 댓글 목록 추가 (양방향)
    // mappedBy: Comment 엔티티에 있는 'item' 필드에 의해 매핑되었음을 명시
    // orphanRemoval: 상품이 삭제되면 댓글도 고아가 되어 삭제되게 설정
    @org.hibernate.annotations.BatchSize(size = 100)
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default // 빌더 사용 시 리스트 초기화 유지
    private List<Comment> comments = new ArrayList<>();

}
