package com.example.HRM.service.admin;
import com.example.HRM.entity.admin.Role;
import com.example.HRM.repository.admin.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class RoleService {
	 @Autowired
	  private RoleRepository roleRepository;
	  
	  public void addAdmin() {
	    Role admin = new Role();
	    admin.setRoleId(1L);
	    admin.setRoleName("Admin");
	    this.roleRepository.save(admin);
	    Role manager = new Role();
	    manager.setRoleId(2L);
	    manager.setRoleName("Manager");
	    this.roleRepository.save(manager);
	    Role customer = new Role();
	    customer.setRoleId(3L);
	    customer.setRoleName("Customer");
	    this.roleRepository.save(customer);
	    Role employee = new Role();
	    employee.setRoleId(4L);
	    employee.setRoleName("Employee");
	    this.roleRepository.save(employee);
	    Role selectors = new Role();
	    selectors.setRoleId(5L);
	    selectors.setRoleName("Training");
	    this.roleRepository.save(selectors);
	    Role accountant = new Role();
	    accountant.setRoleId(5L);
	    accountant.setRoleName("Accountant");
	    this.roleRepository.save(accountant);
	    Role projectHead = new Role();
	    projectHead.setRoleId(6L);
	    projectHead.setRoleName("ProjectHead");
	    this.roleRepository.save(projectHead);
	    Role TL = new Role();
	    TL.setRoleId(7L);
	    TL.setRoleName("TL");
	    this.roleRepository.save(TL);
	    Role training = new Role();
	    training.setRoleId(8L);
	    training.setRoleName("Training");
	    this.roleRepository.save(training);
	    Role superAdmin = new Role();
	    superAdmin.setRoleId(9L);
	    superAdmin.setRoleName("SuperAdmin");
	    this.roleRepository.save(superAdmin);
	  }
	}

