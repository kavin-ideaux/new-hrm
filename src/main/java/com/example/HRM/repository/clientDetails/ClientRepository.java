package com.example.HRM.repository.clientDetails;
import com.example.HRM.entity.clientDetails.ClientProfile;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface ClientRepository extends JpaRepository<ClientProfile, Long> {
	  ClientProfile findByEmail(String paramString);
	  
	  @Query(value = "select c.client_id,c.client_name,r.role_id,r.role_name from client_profile as c join role as r on r.role_id = c.role_id where c.client_id = :client_id", nativeQuery = true)
	  List<Map<String, Object>> getAllClientDetails(Long client_id);
	  
	  @Query(value = "select c.client_id,c.address,c.city,c.client_name,c.confirm_password,c.password,c.country,c.gender,c.email,c.phone_number, c.referral,c.role_id,c.state,c.mobile_number,c.zip_code,c.status,r.role_name from client_profile as c join role as r on r.role_id = c.role_id where c.status = true", nativeQuery = true)
	  List<Map<String, Object>> getAllClientDetailsTrue();
	  
	  @Query(value = "select c.client_id,c.address,c.city,c.client_name,c.confirm_password,c.password,c.country,c.gender,c.email,c.phone_number, c.referral,c.role_id,c.state,c.mobile_number,c.zip_code,c.status,r.role_name from client_profile as c join role as r on r.role_id = c.role_id where c.status = false", nativeQuery = true)
	  List<Map<String, Object>> getAllClientDetailsFalse();
	}
