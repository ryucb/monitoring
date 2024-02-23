package com.spring.monitoring.host.dto.inpt;

import lombok.Data;

@Data
public class UpdatHostStateInputDTO {

	private String name;
	private String ip;
	private String state;
	
}
