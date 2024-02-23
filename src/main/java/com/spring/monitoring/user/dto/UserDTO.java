package com.spring.monitoring.user.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.ObjectUtils;

import com.spring.monitoring.user.dto.inpt.CreateUserInputDTO;
import com.spring.monitoring.user.table.User;
import com.spring.monitoring.utils.SecurityUtil;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDTO{
	
    private Long seq;
    private String userId;
    private String password;
    private String role;
    private String createUser;
    private LocalDateTime createTime;
    private String updateUser;
    private LocalDateTime updateTime;
       
    public static UserDTO createUser(CreateUserInputDTO createUserInputDTO) {
    	
    	return UserDTO.builder()
    			.userId(createUserInputDTO.getUserId())
    			.password(createUserInputDTO.getPassword())
    			.role(createUserInputDTO.getRole())
    			.createUser(SecurityUtil.getCurrentUsername())
    			.createTime(LocalDateTime.now())
    			.updateUser(SecurityUtil.getCurrentUsername())
    			.updateTime(LocalDateTime.now())
    			.build();
    }

    
    public static UserDTO toDTO(User user) {
    	
    	if(ObjectUtils.isEmpty(user)) {
    		return null;
    	}
    	
    	return UserDTO.builder()
    	.seq(user.getSeq())
    	.userId(user.getUserId())
    	.password(user.getPassword())
    	.role(user.getRole())
    	.createUser(user.getCreateUser())
    	.createTime(user.getCreateTime())
    	.updateUser(user.getUpdateUser())
    	.updateTime(user.getUpdateTime())
    	.build();
    }
    
    public static List<UserDTO> toDTOList(List<User> userList) {
    	List<UserDTO> userDTOList = new ArrayList<UserDTO>();
    	
    	userList.stream().forEach(user -> {
    		userDTOList.add(UserDTO.builder()
    		    	.seq(user.getSeq())
    		    	.userId(user.getUserId())
    		    	.password(user.getPassword())
    		    	.role(user.getRole())
    		    	.createUser(user.getCreateUser())
    		    	.createTime(user.getCreateTime())
    		    	.updateUser(user.getUpdateUser())
    		    	.updateTime(user.getUpdateTime())
    		    	.build());
    	});
    	return userDTOList;
    }


}
