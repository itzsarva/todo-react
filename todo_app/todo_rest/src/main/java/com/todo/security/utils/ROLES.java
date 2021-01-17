package com.todo.security.utils;

public enum ROLES {

    ADMIN("admin"),

    USER("user"),

    GUEST("guest");

    private final String role;

    private ROLES(String role) {
        this.role = role;
    }

    public String getRole() {
        return this.role;
    }
}
