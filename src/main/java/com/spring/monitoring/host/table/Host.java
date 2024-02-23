package com.spring.monitoring.host.table;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ObjectUtils;

import com.spring.monitoring.host.dto.HostDTO;
import com.spring.monitoring.host.dto.inpt.UpdateHostInputDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Host {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;    
    @Column
	private String name;
    @Column
	private String ip;
    @Column
	private String state;
    @Column
	private LocalDateTime stateAliveLastTime;
    @Column
	private LocalDateTime createTime;
    @Column
	private String createUser;
    @Column
	private LocalDateTime updateTime;
    @Column
	private String updateUser;
    
    public void updateHost(UpdateHostInputDTO updateHostInputDTO) {
    	
    	if(!updateHostInputDTO.getName().isEmpty()) {
    		this.name = updateHostInputDTO.getName();    		
    	}
    	
    	if(!updateHostInputDTO.getIp().isEmpty()) {
    		this.ip = updateHostInputDTO.getIp();    		
    	}
    }
    
    public void updateHostState(boolean flag) {
    	
    	if(flag) {
    		this.state = "Y";
    		this.stateAliveLastTime = LocalDateTime.now();
    	}else {
    		this.state = "N";
    	}
    	
    }
    
	public static Host toEntity(HostDTO hostDTO) {
		
		if(ObjectUtils.isEmpty(hostDTO)) {
			return null;
		}
		
		return Host.builder()
				.seq(hostDTO.getSeq())
				.name(hostDTO.getName())
				.ip(hostDTO.getIp())
				.state(hostDTO.getState())
				.stateAliveLastTime(hostDTO.getStateAliveLastTime())
				.createTime(hostDTO.getCreateTime())
				.createUser(hostDTO.getCreateUser())
				.updateTime(hostDTO.getUpdateTime())
				.updateUser(hostDTO.getUpdateUser())
				.build();
	}
	
	public static List<Host> toEntityList(List<HostDTO> hostDTOList) {
		List<Host> hostList = new ArrayList<Host>();
		
		hostDTOList.stream().forEach(host -> {
			hostList.add(Host.builder()
			.seq(host.getSeq())
			.name(host.getName())
			.ip(host.getIp())
			.state(host.getState())
			.stateAliveLastTime(host.getStateAliveLastTime())
			.createTime(host.getCreateTime())
			.createUser(host.getCreateUser())
			.updateTime(host.getUpdateTime())
			.updateUser(host.getUpdateUser())
			.build());
		});
		
		return hostList;
	}
    
	
}
