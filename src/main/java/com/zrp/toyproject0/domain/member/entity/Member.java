package com.zrp.toyproject0.domain.member.entity;

import java.util.ArrayList;
import java.util.List;

import com.zrp.toyproject0.domain.comment.entity.Comment;
import com.zrp.toyproject0.domain.item.entity.Item;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@ToString(exclude = {"items", "comments"})
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String displayName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING) // DB에 숫자가 아닌 "USER", "ADMIN" 문자열로 저장되게 함
    private MemberRole role;

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Item> items = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    @Builder.Default
    private List<Comment> comments = new ArrayList<>();

}
