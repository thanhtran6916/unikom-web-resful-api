package com.example.unikomwebresfulapi.config;

import com.example.unikomwebresfulapi.model.UserPrincipal;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of(userPrincipal.getUsername().toString());
    }
}
