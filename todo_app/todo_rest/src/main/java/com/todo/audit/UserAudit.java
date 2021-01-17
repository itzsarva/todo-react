package com.todo.audit;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserAudit implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return Optional.ofNullable(username);
        } catch (Exception ex) {
            return Optional.of("test");
        }
    }
}