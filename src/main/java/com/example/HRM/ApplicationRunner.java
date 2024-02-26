package com.example.HRM;
import com.example.HRM.service.admin.AdminLoginService;
import com.example.HRM.service.admin.RoleService;
import com.example.HRM.service.attendance.ShiftTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {
	  @Autowired
	  private RoleService roleService;
	  
	  @Autowired
	  private AdminLoginService adminLoginService;
	  
	  @Autowired
	  private ShiftTypeService shiftTypeService;
	  
	  public void run(String... args) {
	    this.roleService.addAdmin();
	    this.adminLoginService.addAdminLoginService();
	    this.shiftTypeService.addShiftTypeService();
	  }
	}

