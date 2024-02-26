package com.example.HRM.entity.accounting;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expenseType")
public class ExpenseType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long expenseTypeId;

	private String expenseType;

	public long getExpenseTypeId() {
		return this.expenseTypeId;
	}

	public void setExpenseTypeId(long expenseTypeId) {
		this.expenseTypeId = expenseTypeId;
	}

	public String getExpenseType() {
		return this.expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}
}
