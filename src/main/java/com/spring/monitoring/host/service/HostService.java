package com.spring.monitoring.host.service;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.spring.monitoring.common.dto.ResultDTO;
import com.spring.monitoring.host.dto.HostDTO;
import com.spring.monitoring.host.dto.inpt.CreateHostInputDTO;
import com.spring.monitoring.host.dto.inpt.RetrieveHostStateInputDTO;
import com.spring.monitoring.host.dto.inpt.UpdateHostInputDTO;
import com.spring.monitoring.host.repository.HostRepository;
import com.spring.monitoring.host.table.Host;
import com.spring.monitoring.log.dto.LogDTO;
import com.spring.monitoring.log.service.LogService;
import com.spring.monitoring.utils.SecurityUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HostService {
	
	private final HostRepository hostRepository;
	
	private final LogService logService;
	
	public ResultDTO retrieveHostAll(){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<HostDTO> hostDTOList = HostDTO.toDTOList(hostRepository.findAll());
		
		if(ObjectUtils.isEmpty(hostDTOList) || hostDTOList.size() == 0) {
			
			return new ResultDTO("success", "데이터가 존재하지 않습니다.");
		}
		resultMap.put("hostList", hostDTOList);
		
		return new ResultDTO("success", "조회 성공", resultMap);
	}
	
	public ResultDTO retrieveHostBySeq(String seq) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		HostDTO hostDTO = HostDTO.toDTO(hostRepository.findById(seq).get());
		
		if(ObjectUtils.isEmpty(hostDTO)) {
			return new ResultDTO("success", "데이터가 존재하지 않습니다.");
		}
		
		resultMap.put("host", hostDTO);
		
		return new ResultDTO("success", "조회 성공", resultMap);
	}
	
	public ResultDTO retrieveHostByName(String name) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		HostDTO hostDTO = HostDTO.toDTO(hostRepository.findByName(name));
		
		if(ObjectUtils.isEmpty(hostDTO)) {
			return new ResultDTO("success", "데이터가 존재하지 않습니다.");
		}
		
		resultMap.put("host", hostDTO);
		
		return new ResultDTO("success", "조회 성공", resultMap);
	}
	
	public ResultDTO retrieveHostByIp(String ip) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		HostDTO hostDTO = HostDTO.toDTO(hostRepository.findByIp(ip));
		
		if(ObjectUtils.isEmpty(hostDTO)) {
			return new ResultDTO("success", "데이터가 존재하지 않습니다.");
		}
		
		resultMap.put("host", hostDTO);
		
		return new ResultDTO("success", "조회 성공", resultMap);
		
	}
	
	public ResultDTO createHost(CreateHostInputDTO createHostInputDTO) {
		
		if(ObjectUtils.isEmpty(createHostInputDTO) || ObjectUtils.isEmpty(createHostInputDTO.getName()) || ObjectUtils.isEmpty(createHostInputDTO.getIp())) {
			return new ResultDTO("fail", "필수값 누락.");
		}else if(!ObjectUtils.isEmpty(hostRepository.findByName(createHostInputDTO.getName()))){
			return new ResultDTO("fail", "이미 존재하는 이름입니다.");
		}
		else if(!ObjectUtils.isEmpty(hostRepository.findByName(createHostInputDTO.getIp()))){
			return new ResultDTO("fail", "이미 존재하는 ip입니다");
		}else if(hostRepository.count() > 99) {
			return new ResultDTO("fail", "호스트가 100개입니다.");
		}
		
		hostRepository.save(Host.toEntity(HostDTO.createHost(createHostInputDTO)));
		
		logService.saveLog(LogDTO.builder()
				.userId(SecurityUtil.getCurrentUsername())
				.type("hostAdd")
				.description("ip ::: " + createHostInputDTO.getIp())
				.result("success")
				.createTime(LocalDateTime.now()).build()
				);
		
		return new ResultDTO("success", "호스트 추가 성공");
	}
	
	public ResultDTO updateHost(UpdateHostInputDTO updateHostInputDTO) {
		
		if(ObjectUtils.isEmpty(updateHostInputDTO) || (ObjectUtils.isEmpty(updateHostInputDTO.getName()) && ObjectUtils.isEmpty(updateHostInputDTO.getIp()))) {
			return new ResultDTO("fail", "필수값 누락.");
		}else if(!ObjectUtils.isEmpty(hostRepository.findByName(updateHostInputDTO.getName()))){
			return new ResultDTO("fail", "이미 존재하는 이름입니다.");
		}
		else if(!ObjectUtils.isEmpty(hostRepository.findByName(updateHostInputDTO.getIp()))){
			return new ResultDTO("fail", "이미 존재하는 ip입니다");
		}
		
		Optional<Host> host = hostRepository.findById(updateHostInputDTO.getSeq());
		
		host.get().updateHost(updateHostInputDTO);
		hostRepository.save(host.get());
		
		logService.saveLog(LogDTO.builder()
				.userId(SecurityUtil.getCurrentUsername())
				.type("hostUpdate")
				.description("seq ::: " + updateHostInputDTO.getSeq())
				.result("success")
				.createTime(LocalDateTime.now()).build()
				);
		
		return new ResultDTO("success", "호스트 변경 성공");
	}
	
	public ResultDTO removeHost(String seq) {
		
		hostRepository.deleteById(seq);
		
		logService.saveLog(LogDTO.builder()
				.userId(SecurityUtil.getCurrentUsername())
				.type("hostDelete")
				.description("seq ::: " + seq)
				.result("success")
				.createTime(LocalDateTime.now()).build()
				);
		
		return new ResultDTO("success", "호스트 삭제 성공");
		
	}
	
	public ResultDTO retrieveHostState(RetrieveHostStateInputDTO retrieveHostStateInputDTO){
		
		if(ObjectUtils.isEmpty(retrieveHostStateInputDTO) || ObjectUtils.isEmpty(retrieveHostStateInputDTO.getIp())) {
			return new ResultDTO("fail", "필수값 누락");
		}
		
		String ip = retrieveHostStateInputDTO.getIp();
		
		Host host = hostRepository.findByIp(ip);
		
		InetAddress inetAddress = null;
		try {
		    inetAddress = InetAddress.getByName(ip);
		} catch (UnknownHostException e) {
		    e.printStackTrace();
		}
		
		try {
			
			boolean flag = inetAddress.isReachable(1000);
			
			host.updateHostState(flag);
			hostRepository.save(host);
			
		    if (flag) {		    	
		        return new ResultDTO("success", "Reachable");
		    } else {
		    	return new ResultDTO("success", "Unreachable");
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		    return new ResultDTO("fail", "IOException");
		}
		
	}
	
	public void stateUpdateAll() {
		
		List<Host> hostList = hostRepository.findAll();
				
		hostList.stream().forEach(host -> {
			try {
				host.updateHostState(InetAddress.getByName(host.getIp()).isReachable(1000));
				hostRepository.save(host);
				
			} catch (IOException e) {
				
			}
		});
		
		
		
	}
	

}
