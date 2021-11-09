package com.psycho.psychohelp.appointment.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import com.psycho.psychohelp.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "appointment")
public class Appointment extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 500)
    private String psychoNotes;

    @NotNull
    private Date ScheduleDate;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnore
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "psychologist_id", nullable = false)
    @JsonIgnore
    private Psychologist psychologist;

}
