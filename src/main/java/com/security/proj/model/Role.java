package com.security.proj.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority {

    MODERATOR("MODERATOR"),
    SUPER_ADMIN("SUPER_ADMIN"),
    SIMPLE_USER("SIMPLE_USER");

    private final String vale;

    @Override
    public String getAuthority() {
        return vale;
    }
}