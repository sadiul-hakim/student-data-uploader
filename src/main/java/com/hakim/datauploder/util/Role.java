package com.hakim.datauploder.util;

public enum Role {
    TEACHER("ROLE_TEACHER"),
    PRINCIPAL("ROLE_PRINCIPAL"),
    ADMIN("ROLE_ADMIN");

    private final String role;

    Role(String role){
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
