package com.psycho.psychohelp.patient.service;

import com.psycho.psychohelp.patient.domain.model.entity.LogBook;
import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.patient.domain.persistence.LogBookRepository;
import com.psycho.psychohelp.patient.domain.persistence.PatientRepository;
import com.psycho.psychohelp.patient.domain.service.LogBookService;
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
public class LogBookServiceImpl implements LogBookService {

    @Autowired
    private static String ENTITY = "Patient";

    @Autowired
    private LogBookRepository logBookRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private Validator validator;

    @Override
    public List<LogBook> getAll() {
        return logBookRepository.findAll();
    }

    @Override
    public LogBook getById(Long logBookId) {
        return logBookRepository.findById(logBookId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, logBookId));
    }

    @Override
    public LogBook create(Long patientId, LogBook request) {
        Set<ConstraintViolation<LogBook>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return patientRepository.findById(patientId).map(patient -> {
            request.setPatient(patient);
            return logBookRepository.save(request);
        }).orElseThrow(()-> new ResourceNotFoundException("POST", patientId));
    }

    @Override
    public LogBook update(Long logbookId, LogBook request) {
        Set<ConstraintViolation<LogBook>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return logBookRepository.findById(logbookId).map(logBook ->
                logBookRepository.save(
                        logBook.withLogbookName(request.getLogbookName())
                                .withConsultationReason(request.getConsultationReason())
                                .withPublicHistory(request.getPublicHistory())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, logbookId));
    }

    @Override
    public ResponseEntity<?> delete(Long logbookId) {

        return logBookRepository.findById(logbookId).map(logBook -> {
                logBookRepository.delete(logBook);
                return ResponseEntity.ok().build();})
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, logbookId));
    }
}
