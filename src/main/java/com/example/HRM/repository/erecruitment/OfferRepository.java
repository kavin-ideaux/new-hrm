package com.example.HRM.repository.erecruitment;
import com.example.HRM.entity.erecruitment.Offer;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface OfferRepository extends JpaRepository<Offer, Long> {
	  @Query(value = "select ol.offer_id,ol.acceptance_status,ol.appointment_id,ol.expiry_date,\tol.joining_date,ol.salary_package,ol.candidate_id,ol.certificate_url from offer_letter as ol", nativeQuery = true)
	  List<Map<String, Object>> getAllDetails();
	  
	  @Query(value = "  select o.offer_id, o.acceptance_status, o.joining_date,  o.expiry_date,o.salary_package,a.appointment_id, a.confirmation_status,a.position,  c.candidate_id,c.first_name,c.last_name, c.email_id  from offer_letter as o  join candidate_information as c on c.candidate_id= o.candidate_id  join appointment as a on a.candidate_id=c.candidate_id", nativeQuery = true)
	  List<Map<String, Object>> FindOfferDetails();
	}
