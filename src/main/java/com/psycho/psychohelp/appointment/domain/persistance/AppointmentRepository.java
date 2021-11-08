package com.psycho.psychohelp.appointment.domain.persistance;

import com.psycho.psychohelp.appointment.domain.model.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    //Appointment findByPsychologistName(String psychoName);
    Appointment findByPsychoNotes(String psychoNotes);
}
