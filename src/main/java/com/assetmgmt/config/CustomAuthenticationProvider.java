package com.assetmgmt.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.assetmgmt.repository.UserRepository;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserRepository userRepo;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		String password = authentication.getCredentials().toString();
		List<GrantedAuthority> roles = new ArrayList<>();

		List<Tuple> users = userRepo.checkByEmail(email);
		if (!users.isEmpty()) {
			Tuple tup = users.get(0);
			String userPasswordEncoded = (String) tup.get("password");
			PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

			if ((userPasswordEncoded == null && userPasswordEncoded.isEmpty())
					|| (!encoder.matches(password, userPasswordEncoded))) {
				// if password not match login failed
				return null;
			}

			for (Tuple obj : users) {
				String role = (String) obj.get("roleName");
				if (role != null) {
					roles.add(new SimpleGrantedAuthority(role));
				}
			}

		} else {
			return null;
		}

		return new UsernamePasswordAuthenticationToken(email, password, roles);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}
