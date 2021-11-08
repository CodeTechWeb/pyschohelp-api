package com.psycho.psychohelp.appointment.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class AppointmentResource {
    private Long id;
    private String PsychoNotes;
    private Date ScheduleDate;
    private Date CreatedDate;
}
