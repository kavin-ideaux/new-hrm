package com.example.HRM.repository.eRecruitments;
import com.example.HRM.entity.eRecruitments.Certificate;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface CertificateRepository extends JpaRepository<Certificate, Long> {
	  @Query(value = " select t.trainee_name, t.start_date, t.end_date ,c. certificate_id,c.certificate_issued_date,  c.hospital_name, c.training_program,c.status  from trainee_details as t   join certificate as c on c.trainee_id=t.trainee_id", nativeQuery = true)
	  List<Map<String, Object>> findTrainingCompletedCertificateDetails();
	}
