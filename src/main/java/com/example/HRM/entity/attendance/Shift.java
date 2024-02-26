package com.example.HRM.entity.attendance;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "shift")
public class Shift {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long shiftId;

	private String inTime;

	private String outTime;

	private String shiftType;

	public long getShiftId() {
		return this.shiftId;
	}

	public void setShiftId(long shiftId) {
		this.shiftId = shiftId;
	}

	public String getInTime() {
		return this.inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return this.outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public String getShiftType() {
		return this.shiftType;
	}

	public void setShiftType(String shiftType) {
		this.shiftType = shiftType;
	}
}
