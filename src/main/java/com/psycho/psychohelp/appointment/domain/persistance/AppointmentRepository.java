package com.psycho.psychohelp.appointment.domain.persistance;

import com.psycho.psychohelp.appointment.domain.model.entity.Appointment;
import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByPsychologistId(Long psychologistId);
    List<Appointment> findByPatientId(Long patientId);
    List<Appointment> findByPatientIdAndPsychologistId(Long patientId, Long psychologistId);
}
