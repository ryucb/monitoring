package com.spring.monitoring.login.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.monitoring.common.dto.ResultDTO;
import com.spring.monitoring.jwt.JwtTokenProvider;
import com.spring.monitoring.login.dto.inpt.LoginInputDTO;
import com.spring.monitoring.login.dto.inpt.LogoutInputDTO;
import com.spring.monitoring.login.service.LoginService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class LoginController {
	
	private final LoginService loginService;
    private final JwtTokenProvider jwtTokenProvider;
	
	@GetMapping("/sing-in")
	public ResultDTO login(@RequestBody LoginInputDTO loginInputDTO) {
		return loginService.login(loginInputDTO);
	}
	
	@GetMapping("/sing-out")
	public ResultDTO logout() {
        return loginService.logout();
	}	

}
