package com.spring.monitoring.login.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.spring.monitoring.common.dto.ResultDTO;
import com.spring.monitoring.jwt.JwtTokenProvider;
import com.spring.monitoring.jwt.service.JwtService;
import com.spring.monitoring.log.dto.LogDTO;
import com.spring.monitoring.log.service.LogService;
import com.spring.monitoring.login.dto.inpt.LoginInputDTO;
import com.spring.monitoring.user.dto.UserDTO;
import com.spring.monitoring.user.service.UserService;
import com.spring.monitoring.utils.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final JwtService jwtService;
	private final UserService userService;
	private final LogService logService;
	
	private final JwtTokenProvider jwtTokenProvider;
	
	public ResultDTO login(LoginInputDTO loginInputDTO) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		UserDTO userDTO = userService.retrieveUser(loginInputDTO.getUserId());
		
		if(ObjectUtils.isEmpty(userDTO)) {
			
			return new ResultDTO("fail", "회원정보가 일치하지 않습니다.");
			
		}else {
			
			if(userDTO.getPassword().equals(loginInputDTO.getPassword())) {
				resultMap.put("jwtToken", jwtService.loginIn(userDTO.getUserId(), userDTO.getPassword()));
				
				logService.saveLog(LogDTO.builder()
					.userId(userDTO.getUserId())
					.type("login")
					.description("로그인 성공")
					.result("success")
					.createTime(LocalDateTime.now()).build()
					);
				
				return new ResultDTO("success", "로그인 성공", resultMap);
			}else {
				logService.saveLog(LogDTO.builder()
						.userId(userDTO.getUserId())
						.type("login")
						.description("회원정보가 일치하지 않습니다.")
						.result("fail")
						.createTime(LocalDateTime.now()).build()
						);
				return new ResultDTO("fail", "회원정보가 일치하지 않습니다.");
			}
			
		}
		
	}
	

	public ResultDTO logout() {
		
		logService.saveLog(LogDTO.builder()
				.userId(SecurityUtil.getCurrentUsername())
				.type("logout")
				.description("로그아웃 성공")
				.result("success")
				.createTime(LocalDateTime.now()).build()
				);
		
		return new ResultDTO("success", "로그아웃 성공");
		
    }
}
