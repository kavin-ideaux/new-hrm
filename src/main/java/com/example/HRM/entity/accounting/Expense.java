package com.example.HRM.entity.accounting;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expense")
public class Expense {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long expenseId;

	private String expenseName;

	private long expenseTypeId;

	private Date Date;

	private String amount;

	private String description;

	private boolean status;

	public boolean isStatus() {
		return this.status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public long getExpenseId() {
		return this.expenseId;
	}

	public void setExpenseId(long expenseId) {
		this.expenseId = expenseId;
	}

	public String getExpenseName() {
		return this.expenseName;
	}

	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	public long getExpenseTypeId() {
		return this.expenseTypeId;
	}

	public void setExpenseTypeId(long expenseTypeId) {
		this.expenseTypeId = expenseTypeId;
	}

	public Date getDate() {
		return this.Date;
	}

	public void setDate(Date date) {
		this.Date = date;
	}

	public String getAmount() {
		return this.amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
