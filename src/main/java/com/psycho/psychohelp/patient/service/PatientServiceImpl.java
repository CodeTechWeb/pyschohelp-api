package com.psycho.psychohelp.patient.service;

import com.psycho.psychohelp.patient.domain.model.entity.LogBook;
import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.patient.domain.persistence.LogBookRepository;
import com.psycho.psychohelp.patient.domain.persistence.PatientRepository;
import com.psycho.psychohelp.patient.domain.service.PatientService;
import com.psycho.psychohelp.patient.resource.CreateLogBookResource;
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

@Service
public class PatientServiceImpl implements PatientService {

    private final static String ENTITY = "Patient";

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private LogBookRepository logBookRepository;

    @Autowired
    private Validator validator;

    @Override
    public List<Patient> getAll() {
        return patientRepository.findAll();
    }

    @Override
    public Page<Patient> getAll(Pageable pageable) {
        return patientRepository.findAll(pageable);
    }

    @Override
    public Patient getById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, patientId));
    }

    @Override
    public Patient create(Patient request, Long logBookId) {
        Set<ConstraintViolation<Patient>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return logBookRepository.findById(logBookId).map(logBook -> {
            request.setLogBook(logBook);
            return patientRepository.save(request);
        }).orElseThrow(() -> new ResourceNotFoundException("LOGBOOK", logBookId));
    }

    @Override
    public Patient update(Long patientId, Patient request) {
        Set<ConstraintViolation<Patient>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return patientRepository.findById(patientId).map(patient ->
                patientRepository.save(
                        patient.withFirstName(request.getFirstName())
                                .withLastName(request.getLastName())
                                .withEmail(request.getEmail())
                                .withDate(request.getDate())
                                .withPhone(request.getPhone())
                                .withGender(request.getGender())
                                .withImage(request.getImage())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, patientId));
    }

    @Override
    public Patient getByName(String firstName, String lastName) {
        return patientRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public Patient getByEmail(String email) {
        return patientRepository.findByEmail(email);
    }

    @Override
    public ResponseEntity<?> delete(Long patientId) {
        return patientRepository.findById(patientId).map(patient -> {
            patientRepository.delete(patient);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, patientId));
    }
}
