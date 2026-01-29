package com.zrp.toyproject0.domain.member.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import lombok.Getter;

@Getter
public class MemberResponse extends User {
    
    private String displayName;

    public MemberResponse(
        String username,
        String password,
        String displayName,
        Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
        this.displayName = displayName;
    }

}
