package com.example.HRM.repository.erecruitment;

import com.example.HRM.entity.erecruitment.Appointment;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
	  @Query(value = " select a.appointment_id, a.confirmation_status, a.date, a.position, a.time ,  h.hr_interview_id, h.status as hrInterviewFinalStatus, c.candidate_id,c.first_name,c.last_name,  c.resume_url, c.cover_letter_url,c.email_id,c.education,c.dateof_birth,c.gender  from appointment as a  join candidate_information as c on c.candidate_id= a.candidate_id  join hr_interview as h on h.candidate_id=c.candidate_id", nativeQuery = true)
	  List<Map<String, Object>> findAppointmentDetails();
	}
