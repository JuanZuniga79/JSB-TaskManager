package com.example.javasb_taskms.models;
import lombok.val;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class BearerToken extends AbstractAuthenticationToken {

    private final String value;

    public BearerToken(String value) {
        super(AuthorityUtils.NO_AUTHORITIES); // Pass NO_AUTHORITIES to the superclass constructor
        this.value = value;
    }

    @Override
    public Object getCredentials() {
        return value;
    }

    @Override
    public Object getPrincipal() {
        return value;
    }

    public String getValue() {
        return value;
    }
}
