package com.library.entities;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    READER,
    LIBRARIAN;

    @Override
    public String getAuthority() {
        return name();
    }
}
