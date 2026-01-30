package com.zrp.toyproject0.domain.order.dto;

import java.time.LocalDateTime;
import com.zrp.toyproject0.domain.order.entity.Order;
import com.zrp.toyproject0.domain.order.entity.OrderStatus; // Enum 추가
import lombok.Getter;

@Getter
public class OrderResponse {
    
    private Long id;                // 주문 번호 (수정/삭제 시 필수)
    private Long itemId;            // 상품 번호 (상세보기 이동 시 필수)
    private String itemName;
    private Long orderPrice;
    private Integer count;
    private Long totalPrice;        // 합계 금액 (미리 계산해서 넘기면 편해요)
    private LocalDateTime orderDate;
    private OrderStatus status;     // 주문 상태 (CART, ORDERED 등)

    public static OrderResponse from(Order order) {
        OrderResponse dto = new OrderResponse();
        dto.id = order.getId();
        dto.itemId = order.getItem().getId(); // 연관관계에서 ID 추출
        dto.itemName = order.getItem().getName();
        dto.orderPrice = order.getOrderPrice();
        dto.count = order.getCount();
        dto.totalPrice = order.getOrderPrice() * order.getCount(); // 계산 로직 추가
        dto.orderDate = order.getOrderDate();
        dto.status = order.getStatus();
        return dto;
    }
}