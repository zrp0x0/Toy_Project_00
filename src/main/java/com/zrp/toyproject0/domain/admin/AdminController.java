package com.zrp.toyproject0.domain.admin;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.zrp.toyproject0.domain.comment.entity.Comment;
import com.zrp.toyproject0.domain.comment.repository.CommentRepository;
import com.zrp.toyproject0.domain.member.entity.Member;
import com.zrp.toyproject0.domain.order.entity.Order;
import com.zrp.toyproject0.domain.order.entity.OrderStatus;
import com.zrp.toyproject0.domain.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    
    private final AdminServiceImpl adminService;
    private final CommentRepository commentRepository;
    private final OrderRepository orderRepository;

    @GetMapping("/page")
    public String adminPage() {
        return "adminPage.html";
    }

    @GetMapping("/page/all-member")
    public String allMember(
        Model model
    ) {
        model.addAttribute("memberList", adminService.getAllMember());
        return "allMember.html";
    }

    @GetMapping("/member/{id}")
    public String memberDetail(
        @PathVariable("id") Long id,
        @Qualifier("order") @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable orderPageable,
        @Qualifier("comment") @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable commentPageable,
        Model model
    ) {
        Member member = adminService.getMember(id);
        Page<Order> orderPage = orderRepository.findByUsernameAndStatus(member.getUsername(), OrderStatus.ORDERED, orderPageable);
        Page<Comment> commentPage = commentRepository.findByMember_Username(member.getUsername(), commentPageable);

        model.addAttribute("member", member);
        
        // 이름을 userOrderList -> orderPage로 변경 (템플릿에 맞춤)
        model.addAttribute("orderPage", orderPage);
        
        // 이름을 userCommentList -> commentPage로 변경 (템플릿에 맞춤)
        model.addAttribute("commentPage", commentPage);

        return "memberActivity.html";
    }
    
}
