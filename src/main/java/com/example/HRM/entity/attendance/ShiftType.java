package com.example.HRM.entity.attendance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shift_type")
public class ShiftType {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long shiftTypeId;

	private String shiftName;

	public long getShiftTypeId() {
		return this.shiftTypeId;
	}

	public void setShiftTypeId(long shiftTypeId) {
		this.shiftTypeId = shiftTypeId;
	}

	public String getShiftName() {
		return this.shiftName;
	}

	public void setShiftName(String shiftName) {
		this.shiftName = shiftName;
	}
}
