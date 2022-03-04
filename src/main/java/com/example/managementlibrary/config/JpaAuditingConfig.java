package com.example.managementlibrary.config;

import com.example.managementlibrary.security.MyUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditingConfig {
    @Bean
    public AuditorAware<Long> auditorProvider(){
        return new AuditorAwareImpl();
    }
    public static class AuditorAwareImpl implements AuditorAware<Long>{
        @Override
        public Optional<Long> getCurrentAuditor() {
            Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                MyUser userDetails = (MyUser) authentication.getPrincipal();
                return Optional.ofNullable(userDetails.getId());
            }
            return Optional.ofNullable(null);
        }
    }
}