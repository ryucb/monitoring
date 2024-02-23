package com.spring.monitoring.log.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.spring.monitoring.common.dto.ResultDTO;
import com.spring.monitoring.log.dto.LogDTO;
import com.spring.monitoring.log.dto.inpt.RetrieveLogInputDTO;
import com.spring.monitoring.log.repository.LogRepository;
import com.spring.monitoring.log.table.Log;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LogService {
	
	private final LogRepository logRepository;
	
	public ResultDTO retrieveLogAll() {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Log> logList = logRepository.findAll();
		
		if(ObjectUtils.isEmpty(logList) || logList.size() == 0) {
			return new ResultDTO("success", "데이터가 존재하지 않습니다.");
		}
		
		resultMap.put("logList", logList);
		
		return new ResultDTO("success", "조회성공", resultMap);
	}

	public ResultDTO retrieveLog(RetrieveLogInputDTO retrieveLogInputDTO) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<LogDTO> logDTOList = new ArrayList<LogDTO>();
		
		String userId = retrieveLogInputDTO.getUserId();
		String type = retrieveLogInputDTO.getType();
		String result = retrieveLogInputDTO.getResult();
		
		if(!ObjectUtils.isEmpty(userId)) {
			if(!ObjectUtils.isEmpty(type)) {
				if(!ObjectUtils.isEmpty(result)) {
					logDTOList = LogDTO.toDTOList(logRepository.findByUserIdAndTypeAndResult(userId, type, result));
				}else {
					logDTOList = LogDTO.toDTOList(logRepository.findByUserIdAndType(userId, type));
				}
			}else {
				logDTOList = LogDTO.toDTOList(logRepository.findByUserId(userId));
			}
		}else if(!ObjectUtils.isEmpty(type)){
			
			if(!ObjectUtils.isEmpty(result)) {
				logDTOList = LogDTO.toDTOList(logRepository.findByTypeAndResult(type, result));
			}else {
				logDTOList = LogDTO.toDTOList(logRepository.findByType(type));
			}
		}else {
			return new ResultDTO("fail", "필수값이 존재하지 않습니다.");
		}
		
		resultMap.put("logDTOList", logDTOList);
		
		
		return new ResultDTO("success", "조회성공", resultMap);
	}
	
	public void saveLog(LogDTO logDTO) {
		logRepository.save(Log.toEntity(logDTO));
	}
}
