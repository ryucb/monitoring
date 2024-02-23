package com.spring.monitoring.log.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ObjectUtils;

import com.spring.monitoring.log.table.Log;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogDTO {
		
	private Long seq;
    private String userId;
    private String type;
    private String description;
    private String result;
    private LocalDateTime createTime;
    
    public static LogDTO toDTO(Log log) {
    	
		if(ObjectUtils.isEmpty(log)) {
			return null;
		}
		
    	return LogDTO.builder()
    	.seq(log.getSeq())
    	.userId(log.getUserId())
    	.type(log.getType())
    	.description(log.getDescription())
    	.result(log.getResult())
    	.createTime(log.getCreateTime())
    	.build();
    }
    
    public static List<LogDTO> toDTOList(List<Log> logList) {
    	
    	List<LogDTO> logDTOList = new ArrayList<LogDTO>();
    	
    	logList.stream().forEach(log -> {
    		logDTOList.add(LogDTO.builder()
    		    	.seq(log.getSeq())
    		    	.userId(log.getUserId())
    		    	.type(log.getType())
    		    	.description(log.getDescription())
    		    	.result(log.getResult())
    		    	.createTime(log.getCreateTime())
    		    	.build());
    	});
    	
    	return logDTOList;
    }
    
}
