package com.assetmgmt.config;

import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.nimbusds.jwt.JWTClaimsSet;

//test
@Configuration
@EnableJpaAuditing
public class AuditingConfig {
	// test
	@Bean
	public AuditorAware<Long> auditorProvider() {
		return new SpringSecurityAuditAwareImpl();
	}
}

class SpringSecurityAuditAwareImpl implements AuditorAware<Long> {
	@Override
	public Optional<Long> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()
				|| authentication instanceof AnonymousAuthenticationToken) {
			return Optional.empty();
		}

		Long loginId = null;
			JWTClaimsSet claimsSet = (JWTClaimsSet) authentication.getPrincipal();
			if (claimsSet != null && claimsSet.getClaims().get("user_name") != null) {
				loginId = Long.parseLong((String) claimsSet.getClaims().get("user_name"));
			}
		
		return Optional.ofNullable(loginId);		
	}
}
