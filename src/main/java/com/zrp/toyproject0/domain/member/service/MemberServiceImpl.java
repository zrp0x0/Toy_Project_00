package com.zrp.toyproject0.domain.member.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.zrp.toyproject0.domain.member.dto.MemberRequest;
import com.zrp.toyproject0.domain.member.entity.Member;
import com.zrp.toyproject0.domain.member.entity.MemberRole;
import com.zrp.toyproject0.domain.member.repository.MemberRepository;
import com.zrp.toyproject0.global.exceptions.CustomException;
import com.zrp.toyproject0.global.exceptions.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Override
    public void registerMember(MemberRequest memberRequest) {
        // 중복 체크 반드시 해야지
        if (memberRepository.existsByUsername(memberRequest.getUsername())) {
            throw new CustomException(ErrorCode.DUPLICATE_USERNAME);
        }

        if (memberRepository.existsByUserEmail(memberRequest.getUserEmail())) {
            throw new CustomException(ErrorCode.DUPLICATE_EMAIL);
        }

        memberRequest.setPassword(passwordEncoder.encode(memberRequest.getPassword()));

        Member member = memberRequest.toEntity();
        member.setRole(MemberRole.USER);

        memberRepository.save(member);
    }
    
}
