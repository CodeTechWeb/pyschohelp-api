package com.psycho.psychohelp.patient.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psycho.psychohelp.shared.domain.model.AuditModel;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "logbooks")
public class LogBook extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 200)
    private String logbookName;

    @NotNull
    @Size(max = 200)
    private String publicHistory;

    @NotNull
    @Size(max = 200)
    private String consultationReason;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    @JsonIgnore
    private Patient patient;

}
