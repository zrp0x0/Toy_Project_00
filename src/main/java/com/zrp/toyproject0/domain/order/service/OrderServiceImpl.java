package com.zrp.toyproject0.domain.order.service;

import java.util.List;
import java.util.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.zrp.toyproject0.domain.item.entity.Item;
import com.zrp.toyproject0.domain.item.repository.ItemRepository;
import com.zrp.toyproject0.domain.member.entity.Member;
import com.zrp.toyproject0.domain.member.repository.MemberRepository;
import com.zrp.toyproject0.domain.order.dto.OrderRequest;
import com.zrp.toyproject0.domain.order.entity.Order;
import com.zrp.toyproject0.domain.order.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long createOrder(Authentication auth, OrderRequest orderRequest) {
        
        Long id = -1L;

        Long itemId = orderRequest.getItemId();
        Integer count = orderRequest.getCount();

        Optional<Member> result1 = memberRepository.findByUsername(auth.getName());
        Optional<Item> result2 = itemRepository.findById(itemId);

        if (result1.isPresent() && result2.isPresent()) {
            Order order = Order.createOrder(result1.get(), result2.get(), count);
            id = orderRepository.save(order).getId();
        } else {

        }

        return id; 
    }

    @Override
    public List<Order> getUserOrderList(Authentication auth) {
        Optional<Member> result = memberRepository.findByUsername(auth.getName());
        if (result.isPresent()) {
            return result.get().getOrders();
        }
        return null;
    }
    
}
