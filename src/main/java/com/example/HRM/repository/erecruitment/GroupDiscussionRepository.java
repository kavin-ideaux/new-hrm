package com.example.HRM.repository.erecruitment;
import com.example.HRM.entity.erecruitment.GroupDiscussion;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
public interface GroupDiscussionRepository extends JpaRepository<GroupDiscussion, Long> {
	  @Query(value = "SELECT g.group_discussion_id AS groupDiscussionId, g.completed AS completed, g.date AS date, g.feedback AS feedback, g.format AS groupDiscussionformat, g.in_progress AS inProgress, g.rules AS rules,  g.started AS Started, g.status AS groupDiscussionStatus, g.time AS groupDiscussionTime, g.topic AS groupDiscussionTopic, t.task_id AS taskId, t.completed AS taskCompleted, c.candidate_id AS candidateId, c.first_name AS firstName, c.last_name AS lastName  FROM group_discussion AS g  JOIN candidate_information AS c ON c.candidate_id = g.candidate_id  JOIN task_assigned AS t ON t.candidate_id = c.candidate_id", nativeQuery = true)
	  List<Map<String, Object>> findGroupDiscussionDetails();
	}
