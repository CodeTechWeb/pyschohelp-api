package com.psycho.psychohelp.appointment.resource;

import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AppointmentResource {
    private Long id;
    private String meetUrl;
    private String Motive;
    private String PersonalHistory;
    private String TestRealized;
    private String Treatment;
    private String ScheduleDate;
    private Long psychologistId;
    private Long patientId;
}
