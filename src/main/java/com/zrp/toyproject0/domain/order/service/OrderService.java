package com.zrp.toyproject0.domain.order.service;

import org.springframework.security.core.Authentication;
import com.zrp.toyproject0.domain.order.dto.OrderRequest;

public interface OrderService {
    
    Long createOrder(Authentication auth, OrderRequest orderRequest);
    
}
