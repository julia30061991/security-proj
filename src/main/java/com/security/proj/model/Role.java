package com.security.proj.model;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import java.io.Serializable;

@RequiredArgsConstructor
public enum Role implements GrantedAuthority, Serializable {

    MODERATOR("MODERATOR"),
    SUPER_ADMIN("SUPER_ADMIN"),
    SIMPLE_USER("SIMPLE_USER");

    private String vale;

    Role(String vale) {
        this.vale = vale;
    }

    @Override
    public String getAuthority() {
        return vale;
    }
}