package com.example.HRM.repository.employee;
import com.example.HRM.entity.employee.AwardsPhoto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AwardsPhotoRepository extends JpaRepository<AwardsPhoto, Long> {
	  List<AwardsPhoto> findByAwardsAwardsId(long paramLong);
	}

