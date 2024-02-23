package com.spring.monitoring.host.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.monitoring.host.table.Host;

@Repository
public interface HostRepository extends JpaRepository<Host, String> {
	
	Host findByName(String name);
	
	Host findByIp(String ip);
	
}
