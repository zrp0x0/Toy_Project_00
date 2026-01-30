package com.zrp.toyproject0.domain.order.service;

import java.util.List;
import org.springframework.security.core.Authentication;
import com.zrp.toyproject0.domain.order.dto.OrderRequest;
import com.zrp.toyproject0.domain.order.entity.Order;

public interface OrderService {
    
    Long createOrder(Authentication auth, OrderRequest orderRequest);
    List<Order> getUserOrderList(Authentication auth);
    boolean confirmOrder(Long id);
    
}
