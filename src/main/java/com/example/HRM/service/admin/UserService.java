package com.example.HRM.service.admin;
import com.example.HRM.entity.admin.User;
import com.example.HRM.repository.admin.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	  private UserRepository userRepository;
	  
	  public List<User> singleUser() {
	    return this.userRepository.findAll();
	  }
	  
	  public void saveSingleUser(User user) {
	    this.userRepository.save(user);
	  }
	  
	  public void deleteUserById(Long id) {
	    this.userRepository.deleteById(id);
	  }
	  
	  public User findUserById(Long id) {
	    return this.userRepository.findById(id).get();
	  }
	  
	  public void deleteCompanyById(Long userId) {
	    this.userRepository.deleteById(userId);
	  }
	  
	  public User getCompanyById(Long companyId) {
	    return this.userRepository.findById(companyId).orElse(null);
	  }
	  
	  public User getUserByEmail(String email) {
	    return this.userRepository.findByEmail(email);
	  }
	  
	  public List<User> getUserById(Long userId) {
	    return this.userRepository.findByUserId(userId);
	  }
	}

