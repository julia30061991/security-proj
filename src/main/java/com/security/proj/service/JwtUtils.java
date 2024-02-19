package com.security.proj.service;

import com.security.proj.model.Role;
import com.security.proj.security.JwtAuthentication;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class JwtUtils {

    public static JwtAuthentication generate(Claims claims) {
        final JwtAuthentication jwtInfoToken = new JwtAuthentication();
        jwtInfoToken.setRoles(getRoles(claims));
        jwtInfoToken.setFullname((claims.get("fullname", String.class)));
        jwtInfoToken.setUsername(claims.getSubject());
        return jwtInfoToken;
    }

    private static List<Role> getRoles(Claims claims) {
        final Object object = claims.get("roles");
        Role role = Role.valueOf(object.toString());
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        return roles;
    }
}