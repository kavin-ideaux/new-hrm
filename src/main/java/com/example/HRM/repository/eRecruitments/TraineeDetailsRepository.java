package com.example.HRM.repository.eRecruitments;
import com.example.HRM.entity.eRecruitments.TraineeDetails;
import org.springframework.data.jpa.repository.JpaRepository;
public interface TraineeDetailsRepository extends JpaRepository<TraineeDetails, Long> {
	  long countByStatusTrue();
	}
