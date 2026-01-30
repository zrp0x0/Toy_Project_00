package com.zrp.toyproject0.domain.admin;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zrp.toyproject0.domain.member.entity.Member;
import com.zrp.toyproject0.domain.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl {
    
    private final MemberRepository memberRepository;

    public List<Member> getAllMember() {
        List<Member> list = memberRepository.findAll();
        return list;
    }

    public Member getMember(Long id) {
        return memberRepository.findById(id).get();
    }

}
