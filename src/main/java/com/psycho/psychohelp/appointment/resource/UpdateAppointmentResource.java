package com.psycho.psychohelp.appointment.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class UpdateAppointmentResource {

    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 100)
    private String PsychoNotes;

    @NotNull
    private Date ScheduleDate;
}
