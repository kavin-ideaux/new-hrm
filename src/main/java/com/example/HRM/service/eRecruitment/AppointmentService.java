package com.example.HRM.service.eRecruitment;
import com.example.HRM.entity.erecruitment.Appointment;
import com.example.HRM.repository.erecruitment.AppointmentRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

	 @Autowired
	  private AppointmentRepository appointmentRepository;
	  
	  public List<Appointment> listAll() {
	    return this.appointmentRepository.findAll();
	  }
	  
	  public void SaveAppointment(Appointment appointment) {
	    this.appointmentRepository.save(appointment);
	  }
	  
	  public Appointment findById(Long id) {
	    return this.appointmentRepository.findById(id).get();
	  }
	  
	  public void deleteAppointmentId(Long id) {
	    this.appointmentRepository.deleteById(id);
	  }
	}

