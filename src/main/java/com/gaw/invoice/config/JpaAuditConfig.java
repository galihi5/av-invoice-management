package com.gaw.invoice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class JpaAuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider(){
        return () -> Optional.of("Test User");
        /*
        return () -> {
            System.out.println("Hello world");
            return Optional.of("Test User");
        };
         */

        /*
        return new AuditorAware<String>(){
            @Override
            public Optional<String> getCurrentAuditor() {
                return Optional.of("Test User");
            }
        };
        */
    }
}