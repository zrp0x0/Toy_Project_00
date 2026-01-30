package com.zrp.toyproject0.domain.order.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.zrp.toyproject0.domain.order.dto.OrderRequest;
import com.zrp.toyproject0.domain.order.entity.Order;
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

    @GetMapping("/order/list")
    public String orderList(
        Model model,
        Authentication auth
    ) {
        List<Order> userOrderList = orderService.getUserOrderList(auth);
        model.addAttribute("orders", userOrderList);
        return "userOrderList.html";
    }

    @PatchMapping("/order/confirm/{id}")
    @ResponseBody
    public ResponseEntity<String> orderConfirm(
        @PathVariable("id") Long id
    ) {
        // 이제 주문 확정 및 결제 트랜잭션 처리 및 테스트?도 해보자
        if (orderService.confirmOrder(id)) {
            return ResponseEntity.ok("order confirm success");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("order confirm fail");
        }
    }
    
}
