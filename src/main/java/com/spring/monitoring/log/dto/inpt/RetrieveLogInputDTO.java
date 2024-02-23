package com.spring.monitoring.log.dto.inpt;

import lombok.Data;

@Data
public class RetrieveLogInputDTO {
	
	private String userId;
	private String type;
	private String result;

}
