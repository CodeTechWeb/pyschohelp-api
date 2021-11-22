package com.psycho.psychohelp.patient.domain.persistence;

import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmail(String email);
    //find list of patients from the table appointments where the patient_id is the same as the id of the patient
    //and psychologist_id is the same as the id of the psychologist
    @Query(value = "select * from patients where id in (select patient_id from appointments where psychologist_id = ?1)", nativeQuery = true)
    List<Patient> findPatientsByPsychologistId(Long psychologistId);


}
