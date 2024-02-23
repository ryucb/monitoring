package com.spring.monitoring.host.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ObjectUtils;

import com.spring.monitoring.host.dto.inpt.CreateHostInputDTO;
import com.spring.monitoring.host.table.Host;
import com.spring.monitoring.utils.SecurityUtil;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HostDTO {
	
	private Long seq;
	private String name;
	private String ip;
	private String state;
	private LocalDateTime stateAliveLastTime;
	private LocalDateTime createTime;
	private String createUser;
	private LocalDateTime updateTime;
	private String updateUser;
	
    public static HostDTO createHost(CreateHostInputDTO createHostInputDTO) {
    	
		return HostDTO.builder()
				.name(createHostInputDTO.getName())
				.ip(createHostInputDTO.getIp())
				.state("N")
				.stateAliveLastTime(LocalDateTime.now())
				.createTime(LocalDateTime.now())
				.createUser(SecurityUtil.getCurrentUsername())
				.updateTime(LocalDateTime.now())
				.updateUser(SecurityUtil.getCurrentUsername())
				.build();
    }
	
	public static HostDTO toDTO(Host host) {
		
		if(ObjectUtils.isEmpty(host)) {
			return null;
		}
		return HostDTO.builder()
				.seq(host.getSeq())
				.name(host.getName())
				.ip(host.getIp())
				.state(host.getState())
				.stateAliveLastTime(host.getStateAliveLastTime())
				.createTime(host.getCreateTime())
				.createUser(host.getCreateUser())
				.updateTime(host.getUpdateTime())
				.updateUser(host.getUpdateUser())
				.build();
	}
	
	public static List<HostDTO> toDTOList(List<Host> hostList) {
		List<HostDTO> hostDTOList = new ArrayList<HostDTO>();
		
		hostList.stream().forEach(host -> {
			hostDTOList.add(HostDTO.builder()
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
		
		return hostDTOList;
	}

}
