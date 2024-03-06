package org.artem.flight.system.database.entity;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    MANAGER;

    @Override
    public String getAuthority() {
        return name();
    }
}