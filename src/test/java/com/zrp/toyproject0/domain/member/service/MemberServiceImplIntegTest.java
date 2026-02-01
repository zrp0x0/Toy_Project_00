package com.zrp.toyproject0.domain.member.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.zrp.toyproject0.domain.member.dto.MemberRequest;
import com.zrp.toyproject0.domain.member.entity.Member;
import com.zrp.toyproject0.domain.member.repository.MemberRepository;

import jakarta.transaction.Transactional;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest // 스프링 컨테이너 실행 (DB 연결)
@Transactional // 테스트 끝나면 데이터 롤백 (초기화), 실제 데이터를 넣을 순 없으니깐, 가짜를 넣고 롤백하자
public class MemberServiceImplIntegTest {
    
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("실제 DB 회원가입 성공 테스트")
    void registerMember_Integration_Success() {
        // Given
        MemberRequest request = new MemberRequest();
        request.setUsername("realUser");
        request.setPassword("12341234");
        request.setUserEmail("real@test.com");
        request.setDisplayName("통합테스터");

        // When
        memberService.registerMember(request);

        // Then (DB에서 진짜 조회해보기)
        Member savedMember = memberRepository.findByUsername("realUser").orElseThrow();
        
        assertThat(savedMember.getUserEmail()).isEqualTo("real@test.com");
        assertThat(savedMember.getDisplayName()).isEqualTo("통합테스터");
        // 비밀번호는 암호화되었으므로 원본과 달라야 함
        assertThat(savedMember.getPassword()).isNotEqualTo("12341234");
    }
}
