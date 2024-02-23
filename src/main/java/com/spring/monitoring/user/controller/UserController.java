package com.spring.monitoring.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.monitoring.common.dto.ResultDTO;
import com.spring.monitoring.user.dto.inpt.CreateUserInputDTO;
import com.spring.monitoring.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	
	@PostMapping("/sing-up")
	public ResultDTO createUser(@RequestBody CreateUserInputDTO createUserInputDTO) {
		return userService.createUser(createUserInputDTO);
	}
	

}
