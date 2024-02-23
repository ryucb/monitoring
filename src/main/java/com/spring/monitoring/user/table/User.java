package com.spring.monitoring.user.table;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.util.ObjectUtils;

import com.spring.monitoring.user.dto.UserDTO;

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
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;
    
    @Column
    private String userId;
    @Column
    private String password;
    @Column
    private String role;
    @Column
    private String createUser;
    @Column
    private LocalDateTime createTime;
    @Column
    private String updateUser;
    @Column
    private LocalDateTime updateTime;
    
    public static User toEntity(UserDTO userDTO) {
    	
    	if(ObjectUtils.isEmpty(userDTO)) {
    		return null;
    	}
    	
    	return User.builder()
    	.seq(userDTO.getSeq())
    	.userId(userDTO.getUserId())
    	.password(userDTO.getPassword())
    	.role(userDTO.getRole())
    	.createUser(userDTO.getCreateUser())
    	.createTime(userDTO.getCreateTime())
    	.updateUser(userDTO.getUpdateUser())
    	.updateTime(userDTO.getUpdateTime())
    	.build();
    }
    
    public static List<User> toEntityList(List<UserDTO> userDTOList) {
    	List<User> userList = new ArrayList<User>();
    	
    	userDTOList.stream().forEach(userDTO -> {
    		userList.add(User.builder()
    		    	.seq(userDTO.getSeq())
    		    	.userId(userDTO.getUserId())
    		    	.password(userDTO.getPassword())
    		    	.role(userDTO.getRole())
    		    	.createUser(userDTO.getCreateUser())
    		    	.createTime(userDTO.getCreateTime())
    		    	.updateUser(userDTO.getUpdateUser())
    		    	.updateTime(userDTO.getUpdateTime())
    		    	.build());
    	});
    	return userList;
    }

}
