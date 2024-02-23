package com.spring.monitoring.host.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.monitoring.common.dto.ResultDTO;
import com.spring.monitoring.host.dto.inpt.CreateHostInputDTO;
import com.spring.monitoring.host.dto.inpt.RetrieveHostStateInputDTO;
import com.spring.monitoring.host.dto.inpt.UpdateHostInputDTO;
import com.spring.monitoring.host.service.HostService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/host")
@RequiredArgsConstructor
public class HostController {
	
	private final HostService hostService; 
	
	@GetMapping("/all")
	public ResultDTO retrieveHostAll() {
		return hostService.retrieveHostAll();
	}
	
	@GetMapping("/seq/{seq}")
	public ResultDTO retrieveHostBySeq(@PathVariable String seq) {
		return hostService.retrieveHostBySeq(seq);
	}
	
	@GetMapping("/name/{name}")
	public ResultDTO retrieveHostByName(@PathVariable String name) {
		return hostService.retrieveHostByName(name);
	}
	
	@GetMapping("/ip/{ip}")
	public ResultDTO retrieveHostByIp(@PathVariable String ip) {
		return hostService.retrieveHostByIp(ip);
	}
	
	@PostMapping("/state")
	public ResultDTO retrieveHostState(@RequestBody RetrieveHostStateInputDTO retrieveHostStateInputDTO) {
		return hostService.retrieveHostState(retrieveHostStateInputDTO);
	}
	
	@PostMapping("/admin/save")
	public ResultDTO createHost(@RequestBody CreateHostInputDTO createHostInputDTO) {
		return hostService.createHost(createHostInputDTO);
	}
	
	@PutMapping("/admin/update")
	public ResultDTO updateHost(@RequestBody UpdateHostInputDTO updateHostInputDTO) {
		return hostService.updateHost(updateHostInputDTO);
	}
	
	@DeleteMapping("/admin/remove/{seq}")
	public ResultDTO removeHost(@PathVariable String seq) {
		return hostService.removeHost(seq);
	} 

}
