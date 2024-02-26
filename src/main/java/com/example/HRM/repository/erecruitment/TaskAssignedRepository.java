package com.example.HRM.repository.erecruitment;
import com.example.HRM.entity.erecruitment.TaskAssigned;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface TaskAssignedRepository extends JpaRepository<TaskAssigned, Long> {
	  @Query(value = " select t.task_id, t.comment, t.candidate_id ,t.date,t.start_time, t.end_time,  t.completed, t.started, t.in_progress,t.process_id, t.task_assignee,  t.task_priority from task_assigned as t", nativeQuery = true)
	  List<Map<String, Object>> getTaskAssignedDetails();
	  
	  @Query(value = " select t.task_id as taskId ,t.comment,t.completed,t.date,t.start_time as startTime,t.end_time as endTime,t.in_progress as inProgress,t.started,\t\t t.task_assignee as taskAssignee ,t.task_priority as taskPriority,i.interview_process_id interviewProcessId,i.interview_type as interviewType,\t\t c.candidate_id as candidateId,c.email_id as emailId,c.user_name as userName\t\t from task_assigned as t\t\t  join candidate_information as c on c.candidate_id=t.candidate_id\t\t join interview_process as i on i.candidate_id=c.candidate_id", nativeQuery = true)
	  List<Map<String, Object>> findTaskAssignedDetails();
	}