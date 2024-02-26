package com.example.HRM.repository.clientDetails;
import com.example.HRM.entity.clientDetails.ClientRequirement;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface ClientRequirementRepository extends JpaRepository<ClientRequirement, Long> {
	  @Query(value = " select c.project_id, c.date,c.duration,c.project_name,c.services, cp.client_id,  cp.client_name,cp.email,cp.phone_number  from client_requirements as c  join client_profile as cp on cp.client_id=c.client_id", nativeQuery = true)
	  List<Map<String, Object>> findClientRequirementDetails();
	  
	  @Query(value = "select cr.*,pt.project_type, cp.client_name,s.server_name ,sl.server_type,s.server_id from client_requirements as cr join client_profile as cp on cp.client_id=cr.client_id join server as s on s.server_id=cr.service_id join server_list as sl on sl.fk_server_id=s.server_id join project_type as pt on pt.project_type_id =cr.project_type_id", nativeQuery = true)
	  List<Map<String, Object>> findClientRequirementDetails1();
	  
	  Optional<ClientRequirement> findByClientId(Long paramLong);
	  
	  @Query(value = "select cp.client_id,cp.address,cp.city,cp.client_name,cp.country,cp.email,cp.gender,cp.phone_number, cp.mobile_number,cp.state,cp.zip_code from client_profile as cp", nativeQuery = true)
	  List<Map<String, Object>> getAllRequirementDetails();
	}
