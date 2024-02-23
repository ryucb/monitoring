package com.spring.monitoring.log.table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ObjectUtils;

import com.spring.monitoring.log.dto.LogDTO;

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
public class Log {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seq;
    @Column
    private String userId;
    @Column
    private String type;
    @Column
    private String description;
    @Column
    private String result;
    @Column
    private LocalDateTime createTime;
    
    
    public static Log toEntity(LogDTO logDTO) {
    	
		if(ObjectUtils.isEmpty(logDTO)) {
			return null;
		}
		
    	return Log.builder()
    			.seq(logDTO.getSeq())
    			.userId(logDTO.getUserId())
    			.type(logDTO.getType())
    			.description(logDTO.getDescription())
    			.result(logDTO.getResult())
    			.createTime(logDTO.getCreateTime())
    			.build();
    }
    
    public static List<Log> toEntityList(List<LogDTO> logDTOList) {
    	
    	List<Log> logList = new ArrayList<Log>();
    	
    	logDTOList.stream().forEach(logDTO -> {
    		logList.add(Log.builder()
    			.seq(logDTO.getSeq())
    			.userId(logDTO.getUserId())
    			.type(logDTO.getType())
    			.description(logDTO.getDescription())
    			.result(logDTO.getResult())
    			.createTime(logDTO.getCreateTime())
    			.build());
    	});
    	
    	return logList;
    }

}
