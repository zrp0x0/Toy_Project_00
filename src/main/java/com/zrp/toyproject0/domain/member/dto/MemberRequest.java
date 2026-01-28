package com.zrp.toyproject0.domain.member.dto;

import com.zrp.toyproject0.domain.member.entity.Member;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberRequest {
    
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식에 맞지 않습니다.")
    private String userEmail;

    @NotBlank(message = "닉네임은 필수 입력 값입니다.")
    private String displayName;

    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 4, max = 20, message = "아이디는 4~20자 사이여야 합니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;

    public Member toEntity() {
        Member member = Member.builder()
            .userEmail(this.userEmail)
            .displayName(this.displayName)
            .username(this.username)
            .password(this.password)
            .build();

        return member;
    }
    
}
