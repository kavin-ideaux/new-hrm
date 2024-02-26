package com.example.HRM.repository.accounting;

import com.example.HRM.entity.accounting.ExpenseType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long> {

}
