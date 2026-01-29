package com.zrp.toyproject0.domain.order.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.CreatedDate;
import com.zrp.toyproject0.domain.item.entity.Item;
import com.zrp.toyproject0.domain.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "orders") // order는 SQL 예약어이므로 테이블명을 orders로 지정하는 것이 안전합니다.
@Getter @Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member; // 주문 회원

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private Long orderPrice; // 주문 당시 가격 (Item 가격이 변동될 수 있으므로 기록)
    private Integer count;   // 주문 수량

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime orderDate;

    @Column
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


    public static Order createOrder(Member member, Item item, int count) {
        Order order = new Order();
        order.setMember(member);
        order.setItem(item);
        order.setOrderPrice(item.getPrice());
        order.setCount(count);
        order.setStatus(OrderStatus.CART);

        // Member 엔티티에 주문 목록이 있다면 추가 (양방향 매핑 시)
        member.getOrders().add(order);

        return order;
    }

}
