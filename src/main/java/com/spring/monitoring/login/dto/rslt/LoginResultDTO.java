package com.spring.monitoring.login.dto.rslt;

import com.spring.monitoring.jwt.dto.JwtToken;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResultDTO {
	
	private String msg;
	private JwtToken jwtToken;
	
}
