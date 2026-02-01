package com.zrp.toyproject0.domain.member.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.zrp.toyproject0.domain.member.service.MemberService;
import com.zrp.toyproject0.global.config.SecurityConfig;

@WebMvcTest(MemberController.class) // [1] MemberController만 로딩해!
@Import(SecurityConfig.class) // [3] ★ 핵심: "내 보안 설정도 같이 가져와!"
public class MemberControllerWebMvcTest {
    @Autowired
    MockMvc mvc; // [2] 브라우저인 척 요청을 날려주는 객체

    @MockitoBean // [3] 가짜 서비스 (컨트롤러는 서비스가 필요하니까 빈 껍데기를 끼워줌)
    MemberService memberService;

    @Test
    @DisplayName("회원가입 페이지 접근 테스트 - 성공")
    void registerPage_Success() throws Exception {
        // GET 요청을 날려본다.
        mvc.perform(get("/register"))
            .andExpect(status().isOk()) // 200 OK가 떠야 함
            .andExpect(view().name("register.html")) // 리턴하는 뷰 이름 확인
            .andExpect(model().attributeExists("memberForm")); // 모델에 객체가 담겼는지 확인
    }

    // @Test
    // @DisplayName("회원가입 실패 - 유효성 검사 (이메일 누락)")
    // void register_Fail_Validation() throws Exception {
    //     // POST 요청을 날려본다. (CSRF 토큰 포함)
    //     mvc.perform(post("/register")
    //             .param("username", "testUser")
    //             .param("password", "12341234")
    //             .param("displayName", "테스터")
    //             .param("userEmail", "") // [4] 이메일을 비워서 보냄 (@NotBlank 위반)
    //             // .with(csrf()) // 스프링 시큐리티가 있으면 csrf 토큰 필수
    //     )
    //     .andExpect(status().isOk()) // 폼 에러는 200 OK로 떨어지면서 다시 입력창을 보여줌
    //     .andExpect(view().name("register.html")) // 다시 가입 페이지로 돌아왔는지
    //     .andExpect(model().hasErrors()); // [5] 에러가 발생했는지 검증
    // }
}
