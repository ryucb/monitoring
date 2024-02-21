package com.spring.monitoring.host;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HostController {
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}

}
