package com.psycho.psychohelp.patient.domain.persistence;

import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByFirstNameAndLastName(String firstName, String lastName);
    Patient findByEmail(String email);
}
