package com.example.HRM.repository.employee;

import com.example.HRM.entity.employee.Assest;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface AssestRepository extends JpaRepository<Assest, Long> {
	  @Query(value = " select a.*,b.brand_name,kb.keyboard_brand_name,mb.mouse_brand_name,e.first_name ,e.last_name\t\t\t from assest as a\t\t  join brand as b on b.brand_id=a.brand_id \t\t\t join keyboard_brand as kb on kb.keyboard_brand_id=a.keyboard_brand_id \t\t\t join mouse_brand as mb on mb.mouse_brand_id=a.mouse_brand_id \t\t join employee as e on e.employee_id= a.employee_id  order by a.assest_id desc", nativeQuery = true)
	  List<Map<String, Object>> allAsesstDetails();
	  
	  List<Assest> findByStatusTrue();
	}

