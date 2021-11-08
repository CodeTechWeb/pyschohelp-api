package com.psycho.psychohelp.appointment.domain.service;

import com.psycho.psychohelp.appointment.domain.model.entity.Appointment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {
    List<Appointment> getAll();
    Page<Appointment> getAll(Pageable pageable);
    Appointment getById(Long appointmentId);
    Appointment create(Appointment request);
    Appointment update(Long appointmentId, Appointment request);
    //Appointment getByPsychologistName(String psychoName);
    Appointment getByPsychoNotes(String psychoNotes);
    ResponseEntity<?> delete(Long appointmentId);
}
