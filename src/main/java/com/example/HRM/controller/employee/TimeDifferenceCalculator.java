package com.example.HRM.controller.employee;

import com.example.HRM.entity.employee.EmployeeAttendance;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
public class TimeDifferenceCalculator {

	 public static void main(String[] args) {
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
		    EmployeeAttendance employee = new EmployeeAttendance();
		    String inTimeStr = employee.getInTime();
		    String outTimeStr = employee.getOutTime();
		    LocalTime inTime = LocalTime.parse(inTimeStr, formatter);
		    LocalTime outTime = LocalTime.parse(outTimeStr, formatter);
		    Duration duration = Duration.between(inTime, outTime);
		    long minutes = duration.toMinutes();
		    long hours = minutes / 60L;
		    minutes %= 60L;
		    String formattedDuration = String.format("%02d:%02d", new Object[] { Long.valueOf(hours), Long.valueOf(minutes) });
		    employee.setWorkingHour(formattedDuration);
		    System.out.println("Time difference: " + hours + " hours and " + minutes + " minutes.");
		  }
		}

