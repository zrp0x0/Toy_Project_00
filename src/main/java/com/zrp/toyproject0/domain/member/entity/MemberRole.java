package com.zrp.toyproject0.domain.member.entity;

public enum MemberRole {
    USER("ROLE_USER"), 
    ADMIN("ROLE_ADMIN");

    private final String value;

    MemberRole(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
