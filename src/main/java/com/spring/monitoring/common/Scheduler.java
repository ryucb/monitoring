package com.spring.monitoring.common;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.spring.monitoring.host.repository.HostRepository;
import com.spring.monitoring.host.service.HostService;
import com.spring.monitoring.log.service.LogService;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@EnableScheduling
@RequiredArgsConstructor
public class Scheduler {
	
	private final HostService hostService;
	
	@Scheduled(cron = "0 * * * * *")
	public void run() {
		System.out.println("cron");
		
		hostService.stateUpdateAll();
		
	}

}