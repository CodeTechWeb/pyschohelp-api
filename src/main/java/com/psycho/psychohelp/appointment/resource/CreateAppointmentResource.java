package com.psycho.psychohelp.appointment.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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


}
