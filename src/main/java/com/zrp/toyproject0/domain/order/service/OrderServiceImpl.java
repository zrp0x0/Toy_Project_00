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
import com.zrp.toyproject0.domain.order.entity.OrderStatus;
import com.zrp.toyproject0.domain.order.repository.OrderRepository;

import jakarta.transaction.Transactional;
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
    
    // 결제 로직(가짜)
    @Override
    @Transactional
    public boolean confirmOrder(
        Long id
    ) {
        Optional<Order> result1 = orderRepository.findById(id);
        if (result1.isPresent()) {
            Order order = result1.get();
            Integer orderCount = order.getCount();
            Integer itemCount = order.getItem().getCount();
            Integer changeItemCount = itemCount - orderCount;
            System.out.println("여기 1");
            if (changeItemCount >= 0) {
                // 실제 결제 로직이 있다면 이 부분에 실행
                System.out.println("여기 2");
                order.getItem().setCount(changeItemCount); 
                order.setStatus(OrderStatus.ORDERED);
                // orderRepository.save(order);
                return true;
            } else {
                // 구매 못함 처리
                return false;
            }
        } else {
            return false;
        }
    }

}
