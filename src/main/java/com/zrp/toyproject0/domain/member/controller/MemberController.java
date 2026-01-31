package com.zrp.toyproject0.domain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.zrp.toyproject0.domain.member.dto.MemberRequest;
import com.zrp.toyproject0.domain.member.service.MemberService;
import com.zrp.toyproject0.global.exceptions.CustomException;
import com.zrp.toyproject0.global.exceptions.ErrorCode;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {
    
    private final MemberService memberService;


    // 회원가입
    @GetMapping("/register")
    public String register(
        Model model
    ) {
        model.addAttribute("memberForm", new MemberRequest());
        return "register.html";
    }

    @PostMapping("/register")
    public String register(
        @Valid @ModelAttribute("memberForm") MemberRequest memberRequest,
        BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return "register.html";
        }
        try {
            memberService.registerMember(memberRequest);    
        } catch (CustomException e) {
            if (e.getErrorCode().equals(ErrorCode.DUPLICATE_USERNAME)) {
                bindingResult.rejectValue("username", "duplicate", e.getErrorCode().getMessage());
            } else if (e.getErrorCode().equals(ErrorCode.DUPLICATE_EMAIL)) {
                bindingResult.rejectValue("userEmail", "duplicate", e.getErrorCode().getMessage());
            }
            return "register.html";
        }
        
        return "redirect:/login";
    }


    // 로그인
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

}
