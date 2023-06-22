package com.onerivet.deskbook.config.security;

import java.util.List;

import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;

public class CustomValidator implements OAuth2TokenValidator<Jwt> {

	private final List<String> audiences;

	public CustomValidator(List<String> audiences) {
		this.audiences= audiences;
	}

	@Override
	public OAuth2TokenValidatorResult validate(Jwt jwt) {
		if (jwt.getAudience().containsAll(audiences)) {
			return OAuth2TokenValidatorResult.success();
		} else {
			OAuth2Error error = new OAuth2Error(OAuth2ErrorCodes.INVALID_REQUEST, "Invalid audience", null);
			return OAuth2TokenValidatorResult.failure(error);
		}
	}
}
