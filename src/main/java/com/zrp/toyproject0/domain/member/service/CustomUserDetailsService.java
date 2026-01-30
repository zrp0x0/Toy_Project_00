package com.zrp.toyproject0.domain.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.zrp.toyproject0.domain.member.dto.MemberResponse;
import com.zrp.toyproject0.domain.member.entity.Member;
import com.zrp.toyproject0.domain.member.entity.MemberRole;
import com.zrp.toyproject0.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{
    
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> result = memberRepository.findByUsername(username);        
        if (result.isEmpty()) {

        }
        
        Member member = result.get();
        List<GrantedAuthority> authorization = new ArrayList<>();

        // 권한 부여
        if (member.getRole().equals(MemberRole.USER)) {
            authorization.add(new SimpleGrantedAuthority("user"));
        } else if (member.getRole().equals(MemberRole.ADMIN)) {
            authorization.add(new SimpleGrantedAuthority("admin"));
        }

        return new MemberResponse(member.getUsername(), member.getPassword(), member.getDisplayName(), authorization);
    }

}
