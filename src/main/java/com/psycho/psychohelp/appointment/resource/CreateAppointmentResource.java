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
    @Size(max = 100)
    private String PsychoNotes;

}
