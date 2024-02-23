package com.spring.monitoring.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.monitoring.user.table.User;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

	User findByUserId(String userId);
	
}
