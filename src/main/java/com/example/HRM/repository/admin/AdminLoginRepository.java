package com.example.HRM.repository.admin;
import com.example.HRM.entity.admin.AdminLogin;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface AdminLoginRepository extends JpaRepository<AdminLogin, Long> {
	  AdminLogin findByEmail(String paramString);

		@Query(value=" select a.id,a.confirm_password,a.email,a.name,a.password,a.role_type,a.status,a.date,a.intime,a.role_id"
				+ " from admin_login as a"
				+ " where a.id=:id and a.role_id=:role_id", nativeQuery = true)
		List<Map<String, Object>> getAllEmployeesWithDetailsWithId(@Param("id") Long admin_id,@Param("role_id") Long role_id);
		
	}
