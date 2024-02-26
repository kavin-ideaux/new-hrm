package com.example.HRM.repository.admin;
import com.example.HRM.entity.admin.User;
import java.util.List;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface UserRepository extends JpaRepository<User, Long> {
	  User findByEmail(String paramString);
	  
	  @Query(value = "select u.user_id as userId,u.address,u.city,u.country,u.email,u.location,u.mobile_number as mobileNumber,u.password,u.role_id as roleId,u.username,u.role_type as roleType,r.role_name as roleName from user as u join role as r on r.role_id = u.role_id", nativeQuery = true)
	  List<Map<String, Object>> getManagerDetails();
	  
	  @Query(value = "select u.user_id as userId,u.address,u.city,u.country,u.email,u.location,u.mobile_number as mobileNumber,u.password,u.role_id as roleId,u.username,u.role_type as roleType,r.role_name as roleName from user as u join role as r on r.role_id = u.role_id where u.user_id =:user_id", nativeQuery = true)
	  List<Map<String, Object>> getManagerDetailsById(Long user_id);
	  
	  @Query(value = "select a.* ,r.role_name from admin_login as a join role  as r on r.role_id = a.role_id where a.id=:id", nativeQuery = true)
	  List<Map<String, Object>> getAllAdminDetailsById(Long id);
	  
	  List<User> findByUserId(Long paramLong);

		@Query(value = "SELECT u.user_id, u.status,r.role_id,r.role_name " + " FROM user AS u "
				+ " WHERE u.user_id = :userId"
				+ " ", nativeQuery = true)
		Map<String, Object> getManagerDetailsById2(@Param("userId") long userId);

		@Query(value=" select a.user_id,a.confirm_password,a.email,a.username,a.password,a.role_type,a.status,a.date,a.intime,a.role_id"
				+ " from user  as a "
				+ " where a.user_id =:id and a.role_id=:role_id", nativeQuery = true)
		List<Map<String, Object>> getAllEmployeesWithDetailsWithIdUser(@Param("id") Long admin_id,@Param("role_id") Long role_id);

		@Query(value = "SELECT u.user_id, u.status,r.role_id,r.role_name " + " FROM user AS u "
				+ " join role as r on r.role_id=u.role_id " + " WHERE u.user_id = :userId"
				+ " and u.role_id = :roleID", nativeQuery = true)
		Map<String, Object> getManagerDetailsById2(@Param("userId") long userId, @Param("roleID") long roleID);

	}