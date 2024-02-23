package com.spring.monitoring.log.repository;

import java.awt.print.Pageable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.monitoring.log.table.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, String> {
		
	List<Log> findByUserId(String userId);
	
	List<Log> findByType(String type);
	
	List<Log> findByUserIdAndType(String userId, String type);
	
	List<Log> findByTypeAndResult(String type, String result);
	
	List<Log> findByUserIdAndTypeAndResult(String userId, String type, String result);

}
