package com.example.HRM.repository.erecruitment;

import com.example.HRM.entity.erecruitment.Training;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface TrainingRepository extends JpaRepository<Training, Long> {
	  @Query(value = " select t.trainee_id as traineeId, t.completed,t.start_date as startDate,t.status,t.end_date as endDate, t.started,t.in_progress as inProgress, o.offer_id as offerId,o.acceptance_status as offerAcceptanceStatus,c.candidate_id as candidateId, c.first_name as firstName,c.last_name as lastName, c.job_role as jobRole,c.email_id as emailId from training_details as t join candidate_information as c on c.candidate_id=t.candidate_id join offer_letter as o on o.candidate_id=c.candidate_id", nativeQuery = true)
	  List<Map<String, Object>> findTrainingDetails();
	}

