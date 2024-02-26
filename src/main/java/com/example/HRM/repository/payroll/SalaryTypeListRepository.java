package com.example.HRM.repository.payroll;
import com.example.HRM.entity.payroll.SalaryTypeList;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
public interface SalaryTypeListRepository extends JpaRepository<SalaryTypeList, Long> {
	  Optional<SalaryTypeList> findByEmployeeId(Long paramLong);
	  
	  void save(Optional<SalaryTypeList> paramOptional);
	}
