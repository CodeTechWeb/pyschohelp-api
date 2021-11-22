package com.psycho.psychohelp.appointment.service;

import com.psycho.psychohelp.appointment.domain.model.entity.Appointment;
import com.psycho.psychohelp.appointment.domain.persistance.AppointmentRepository;
import com.psycho.psychohelp.appointment.domain.service.AppointmentService;
import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.patient.domain.persistence.PatientRepository;
import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import com.psycho.psychohelp.psychologist.domain.persistence.PsychologistRepository;
import com.psycho.psychohelp.shared.exception.ResourceNotFoundException;
import com.psycho.psychohelp.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final static String ENTITY = "Appointment";

    private final AppointmentRepository appointmentRepository;
    private final Validator validator;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PsychologistRepository psychologistRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository, Validator validator) {
        this.appointmentRepository = appointmentRepository;
        this.validator = validator;
    }

    @Override
    public List<Appointment> getAll() {
        return appointmentRepository.findAll();
    }


    @Override
    public Appointment getById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, appointmentId));
    }

    //create an appointment with psychologist id and patient id

    @Override
    public Appointment create(Appointment request, Long psychologistId, Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new ResourceNotFoundException("Patient not found with Id " + patientId));
        Psychologist psychologist = psychologistRepository.findById(psychologistId).orElseThrow(() -> new ResourceNotFoundException("Psychologist not found with Id " + psychologistId));
        Set<ConstraintViolation<Appointment>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        request.setPatient(patient);
        request.setPsychologist(psychologist);
        return appointmentRepository.save(request);
    }

    @Override
    public Appointment update(Long appointmentId, Appointment request) {
        Set<ConstraintViolation<Appointment>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return appointmentRepository.findById(appointmentId).map(appointment ->
                        appointmentRepository.save(
                                appointment.withMeetUrl(request.getMeetUrl())
                                        .withScheduleDate(request.getScheduleDate())
                                        .withPersonalHistory(request.getPersonalHistory())
                                        .withMotive(request.getMotive())
                                        .withTestRealized(request.getTestRealized())
                                        .withTreatment(request.getTreatment())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, appointmentId));
    }

    @Override
    public List<Appointment> getByPsychologistId(Long psychologistId) {
        return appointmentRepository.findByPsychologistId(psychologistId);

    }

    @Override
    public List<Appointment> getByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<Appointment> getByPatientIdAndPsychologistId(Long patientId, Long psychologistId) {
        return appointmentRepository.findByPatientIdAndPsychologistId(patientId, psychologistId);
    }

    @Override
    public List<Patient> getPatientsByPsychologistId(Long psychologistId) {
        return patientRepository.findPatientsByPsychologistId(psychologistId);
    }

    @Override
    public ResponseEntity<?> delete(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).map(appointment -> {
            appointmentRepository.delete(appointment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, appointmentId));
    }
}
