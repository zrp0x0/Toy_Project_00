package com.zrp.toyproject0.domain.order.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import com.zrp.toyproject0.domain.order.dto.OrderRequest;
import com.zrp.toyproject0.domain.order.dto.OrderResponse;
import com.zrp.toyproject0.domain.order.entity.Order;
import com.zrp.toyproject0.domain.order.entity.OrderStatus;

public interface OrderService {
    
    Long createOrder(Authentication auth, OrderRequest orderRequest);
    List<Order> getUserOrderList(Authentication auth);
    boolean confirmOrder(Long id);
    boolean deleteOrder(Long id);

    Page<OrderResponse> getOrdersByStatus(String username, OrderStatus status, Pageable pageable);
    
}
