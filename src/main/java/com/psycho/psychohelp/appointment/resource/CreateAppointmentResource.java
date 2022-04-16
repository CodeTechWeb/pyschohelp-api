package com.psycho.psychohelp.appointment.resource;

import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class CreateAppointmentResource {
    @NotNull
    @NotBlank
    @Size(max = 200)
    private String meetUrl;
    @NotNull
    @NotBlank
    @Size(max = 200)
    private String Motive;
    @NotNull
    @NotBlank
    @Size(max = 200)
    private String PersonalHistory;
    @NotNull
    @NotBlank
    @Size(max = 200)
    private String TestRealized;
    @NotNull
    @NotBlank
    @Size(max = 200)
    private String Treatment;
    @NotNull
    @NotBlank
    @Size(max = 20)
    private String ScheduleDate;


    @NotNull
    private Date ScheduleDate;

    @NotNull
    private Date CreatedAt;

    private Long patient_id;

    private Long psychologist_id;
}
