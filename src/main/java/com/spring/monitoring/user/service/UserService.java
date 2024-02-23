package com.spring.monitoring.user.service;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.spring.monitoring.common.dto.ResultDTO;
import com.spring.monitoring.host.dto.HostDTO;
import com.spring.monitoring.user.dto.UserDTO;
import com.spring.monitoring.user.dto.inpt.CreateUserInputDTO;
import com.spring.monitoring.user.repository.UserRepository;
import com.spring.monitoring.user.table.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	
    public UserDTO retrieveUser(String userId) {
        return UserDTO.toDTO(userRepository.findByUserId(userId));
    }
    
    public ResultDTO createUser(CreateUserInputDTO createUserInputDTO) {
    	
    	if(ObjectUtils.isEmpty(createUserInputDTO) || ObjectUtils.isEmpty(createUserInputDTO.getUserId()) || ObjectUtils.isEmpty(createUserInputDTO.getPassword())) {
    		return  new ResultDTO("fail", "필수값 누락");
		}else if(!ObjectUtils.isEmpty(retrieveUser(createUserInputDTO.getUserId()))) {
			return  new ResultDTO("fail", "이미 가입된 아이디입니다.");
		}
    	
    	userRepository.save(User.toEntity(UserDTO.createUser(createUserInputDTO)));
    	return  new ResultDTO("success", "유저 추가 성공");
    }

}
