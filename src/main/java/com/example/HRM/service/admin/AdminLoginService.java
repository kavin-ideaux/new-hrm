package com.example.HRM.service.admin;

import com.example.HRM.entity.admin.AdminLogin;
import com.example.HRM.repository.admin.AdminLoginRepository;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.MalformedInputException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import javax.sql.rowset.serial.SerialBlob;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

@Service
public class AdminLoginService {

	@Autowired
	  private AdminLoginRepository adminLoginRepository;
	  
	public void addAdminLoginService() {
	    // First AdminLogin
	    AdminLogin login = new AdminLogin();
	    login.setId(1);
	    login.setEmail("hrm.ideaux@gmail.com");
	    login.setPassword("ideaux@9865");
	    login.setConfirmPassword("ideaux@9865");
	    login.setName("ideaux");
	    login.setRoleId(1);
	    login.setStatus(true);
	    login.setRoleType("Admin");
	    try {
	        URL imageUrl = new URL("https://cdn.pixabay.com/photo/2020/07/14/13/07/icon-5404125_1280.png");
	        HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
	        int responseCode = connection.getResponseCode();

	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            byte[] imageBytes = StreamUtils.copyToByteArray(connection.getInputStream());
	            Blob imageBlob = new SerialBlob(imageBytes);
	            login.setImage(imageBlob);
	            
	            // Save the entity with the image to the repository
	            adminLoginRepository.save(login);
	        } else {
	            System.err.println("Failed to download image. HTTP error code: " + responseCode);
	        }

	    } catch (MalformedInputException e) {
	        // Handle URL format issues
	        e.printStackTrace();
	    }  catch (SQLException e) {
	        // Handle issues with the Blob or database operations
	        e.printStackTrace();
	    } catch (Exception e) {
	        // Catch any other unexpected exceptions
	        e.printStackTrace();
	    }


	    // Second AdminLogin
	    AdminLogin superAdmin = new AdminLogin();
	    superAdmin.setId(2);
	    superAdmin.setEmail("superadmin@gmail.com");
	    superAdmin.setPassword("123456");
	    superAdmin.setConfirmPassword("123456");
	    superAdmin.setName("vijay");
	    superAdmin.setStatus(true);
	    superAdmin.setRoleId(9);
	    superAdmin.setRoleType("SuperAdmin");
	    try {
	        // Download the image from the URL
	        URL imageUrl = new URL("https://cdn.pixabay.com/photo/2014/03/24/17/19/teacher-295387_1280.png");
	        byte[] imageBytes = StreamUtils.copyToByteArray(imageUrl.openStream());
	        Blob imageBlob = new SerialBlob(imageBytes);
	        superAdmin.setImage(imageBlob);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    adminLoginRepository.save(superAdmin);
	}

	  
	  public boolean authenticateUser(String email, String password) {
	    AdminLogin user = this.adminLoginRepository.findByEmail(email);
	    if (user != null)
	      return compareRawPasswords(password, user.getPassword()); 
	    return false;
	  }
	  
	  private boolean compareRawPasswords(String rawPassword, String storedPassword) {
	    return rawPassword.equals(storedPassword);
	  }
	  
	  public List<AdminLogin> listAll() {
	    return this.adminLoginRepository.findAll();
	  }
	  
	  public void saveOrUpdate(AdminLogin complaints) {
	    this.adminLoginRepository.save(complaints);
	  }
	  
	  public AdminLogin getById(long id) {
	    return this.adminLoginRepository.findById(Long.valueOf(id)).get();
	  }
	  
	  public void deleteById(long id) {
	    this.adminLoginRepository.deleteById(Long.valueOf(id));
	  }
	  
	  public Optional<AdminLogin> getById1(long id) {
	    return Optional.of(this.adminLoginRepository.findById(Long.valueOf(id)).get());
	  }
	}

