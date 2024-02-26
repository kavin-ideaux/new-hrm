package com.example.HRM.repository.employee;
import com.example.HRM.entity.employee.Department;
import org.springframework.data.jpa.repository.JpaRepository;
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
