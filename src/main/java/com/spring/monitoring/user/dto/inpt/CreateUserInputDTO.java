package com.spring.monitoring.user.dto.inpt;

import java.time.LocalDateTime;

import com.spring.monitoring.host.dto.HostDTO;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserInputDTO {
	
	private String userId;
	private String password;
	private String role;

}
