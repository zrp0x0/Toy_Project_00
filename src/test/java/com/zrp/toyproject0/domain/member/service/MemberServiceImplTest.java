package com.zrp.toyproject0.domain.member.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.zrp.toyproject0.domain.member.dto.MemberRequest;
import com.zrp.toyproject0.domain.member.entity.Member;
import com.zrp.toyproject0.domain.member.repository.MemberRepository;
import com.zrp.toyproject0.global.exceptions.CustomException;
import com.zrp.toyproject0.global.exceptions.ErrorCode;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

//
import static org.mockito.BDDMockito.*;
import static org.assertj.core.api.Assertions.*;
//

@ExtendWith(MockitoExtension.class)
public class MemberServiceImplTest {
    
    @InjectMocks
    MemberServiceImpl memberService; // 테스트 대상

    @Mock
    MemberRepository memberRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 성공 테스트")
    void registerMember_Success() {
        // Given (준비)
        MemberRequest request = new MemberRequest();
        request.setUsername("testUser");
        request.setPassword("12341234");
        request.setUserEmail("test@test.com");
        request.setDisplayName("테스터");

        // 1. 아이디 중복 아님
        given(memberRepository.existsByUsername("testUser")).willReturn(false);
        // 2. 이메일 중복 아님
        given(memberRepository.existsByUserEmail("test@test.com")).willReturn(false);
        // 3. 비밀번호 암호화하면 "encodedPw"를 리턴해라
        given(passwordEncoder.encode("12341234")).willReturn("encodedPw");
    
        // When (실행)
        memberService.registerMember(request);

        // Then (검증)
        // save 메서드가 호출되었는지 확인 (즉, 저장이 잘 일어났는지)
        then(memberRepository).should(times(1)).save(any(Member.class));
        // 암호화가 호출되었는지 확인
        then(passwordEncoder).should(times(1)).encode("12341234");
        
    }

    @Test
    @DisplayName("회원가입 실패 - 아이디 중복")
    void registerMember_Fail_DuplicateUsername() {
        // Given
        MemberRequest request = new MemberRequest();
        request.setUsername("duplicateUser");
        request.setPassword("1234");
        request.setUserEmail("a@a.com");

        // "아이디가 이미 존재한다(true)"고 설정
        given(memberRepository.existsByUsername("duplicateUser")).willReturn(true);

        // When & Then (실행하면서 예외 검증)
        assertThatThrownBy(() -> memberService.registerMember(request))
            .isInstanceOf(CustomException.class) // CustomException이 터져야 함
            .hasFieldOrPropertyWithValue("errorCode", ErrorCode.DUPLICATE_USERNAME); // 에러 코드가 맞는지 확인

        // 이메일 체크나 저장은 일어나면 안 됨 (순서상 아이디 체크가 먼저니까)
        then(memberRepository).should(never()).existsByUserEmail(anyString());
        then(memberRepository).should(never()).save(any(Member.class));
    }
    
    // @Test
    // @DisplayName("회원가입 실패 - 이메일 중복")
    // void registerMember_Fail_DuplicateEmail() {
    //     // Given
    //     MemberRequest request = new MemberRequest();
    //     request.setUsername("newUser");
    //     request.setUserEmail("duplicate@test.com");

    //     // 아이디는 중복 아님
    //     given(memberRepository.existsByUsername("newUser")).willReturn(false);
    //     // 이메일은 이미 존재함(true)
    //     given(memberRepository.existsByUserEmail("duplicate@test.com")).willReturn(true);

    //     // When & Then
    //     assertThatThrownBy(() -> memberService.registerMember(request))
    //         .isInstanceOf(CustomException.class)
    //         .hasFieldOrPropertyWithValue("errorCode", ErrorCode.DUPLICATE_EMAIL);
            
    //     // 저장은 일어나면 안 됨
    //     then(memberRepository).should(never()).save(any(Member.class));
    // }

}
