package com.example.HRM.repository.erecruitment;
import com.example.HRM.entity.erecruitment.InterviewSchedule;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface InterviewSchedulingRepository extends JpaRepository<InterviewSchedule, Long> {
	  @Query(value = " select i.interview_schedule_id as interviewScheduleId, i.canceled, i.completed, i.scheduled, i.cancellation_reason as cancellationReason, i.date, \t         i.end_time as endTime, i.interview_format as interviewFormat, i.interview_type as interviewType, i.interviewer_contact as interviewerContact, i.interviewer_name as interviewerName, i.start_time as startTime, i.status, \t       c.candidate_id as candidateId, c.user_name as userName, c.mobile_number as mobileNumber, c.email_id as emailId, c.job_role as jobRole\t         from interview_schedule as i \t         join candidate_information as c on c.candidate_id = i.candidate_id", nativeQuery = true)
	  List<Map<String, Object>> FindInterviewSchedulingDetails();
	  
	  @Query(value = "  select  i.date,i.start_time, c.first_name, c.last_name,   case when day(i.date) = day(curdate()) and   month(i.date) = month(curdate()) and   year(i.date) = year(curdate()) then 'you will be interviewed today'   when day(i.date) = day(curdate() + interval 1 day) and   month(i.date) = month(curdate() + interval 1 day) and   year(i.date) = year(curdate() + interval 1 day) then 'you will have an interview tomorrow'   when day(i.date) = day(curdate() + interval 2 day) and   month(i.date) = month(curdate() + interval 2 day) and   year(i.date) = year(curdate() + interval 2 day) then 'you will have an interview the day after tomorrow'   else 'no special message'   end as interview_notification   from interview_schedule as i   join candidate_information as c on c.candidate_id = i.candidate_id   where date(i.date) between curdate() and curdate() + interval 3 day", nativeQuery = true)
	  List<Map<String, Object>> findInterviewScheduleNotification();
	}