package com.spring.monitoring.log.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.monitoring.common.dto.ResultDTO;
import com.spring.monitoring.log.dto.inpt.RetrieveLogInputDTO;
import com.spring.monitoring.log.service.LogService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class LogController {
	
	private final LogService logService;
	
	@GetMapping("/admin/all")
	public ResultDTO retrieveLogAll() {
		return logService.retrieveLogAll();
	}
	
	@GetMapping("/admin")
	public ResultDTO retrieveLog(RetrieveLogInputDTO retrieveLogInputDTO) {
		return logService.retrieveLog(retrieveLogInputDTO);
	}

}
