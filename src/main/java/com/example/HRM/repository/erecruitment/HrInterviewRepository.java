package com.example.HRM.repository.erecruitment;
import com.example.HRM.entity.erecruitment.HrInterview;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface HrInterviewRepository extends JpaRepository<HrInterview, Long> {
	  @Query(value = " select h.hr_interview_id, h.candidate_questions, h.completed,h.date,h.feedback,h.in_progress,h.interviewer_contact,   h.interviewer_name,h.started,h.status,h.time ,c.candidate_id,c.last_name,c.first_name,  g.group_discussion_id,g. status as groupDiscussionFinalStatus  from hr_interview as h  join candidate_information as c on c.candidate_id=h.candidate_id  join group_discussion as g on g.candidate_id=c.candidate_id", nativeQuery = true)
	  List<Map<String, Object>> findHrInterviewDetails();
	}

