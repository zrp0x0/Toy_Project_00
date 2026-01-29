package com.zrp.toyproject0.domain.order.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.zrp.toyproject0.domain.order.dto.OrderRequest;
import com.zrp.toyproject0.domain.order.service.OrderService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/order/create")
    @ResponseBody
    public ResponseEntity<String> orderCreate(
        Authentication auth,
        @RequestBody OrderRequest orderRequest,
        RedirectAttributes redirectAttributes
    ) {
        if (auth == null) {
            return ResponseEntity.status(401).body("need login");
        }

        orderService.createOrder(auth, orderRequest);
        redirectAttributes.addAttribute("itemId", orderRequest.getItemId());
        return ResponseEntity.ok("create order success");
    }
    
}
