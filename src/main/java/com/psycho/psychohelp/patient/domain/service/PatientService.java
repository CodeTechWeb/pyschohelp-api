package com.psycho.psychohelp.patient.domain.service;

import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PatientService {
    List<Patient> getAll();
    Page<Patient> getAll(Pageable pageable);
    Patient getById(Long patientId);
    Patient create(Patient request);
    Patient update(Long patientId, Patient request);
    Patient getByName(String firstName, String lastName);
    Patient getByEmail(String email);
    ResponseEntity<?> delete(Long logbookId);
}
