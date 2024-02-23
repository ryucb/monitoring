package com.spring.monitoring.common.dto;

import java.util.Map;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Data;

@Data
public class ResultDTO {

    private String resultCode;
    private String resultMsg;
    private Map<String, Object> resultData;
    
    
	public ResultDTO(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	public ResultDTO(String resultCode, String resultMsg, Map<String, Object> resultData) {
		this.resultCode = resultCode;
		this.resultData = resultData;
		this.resultMsg = resultMsg;
	}
   

}
