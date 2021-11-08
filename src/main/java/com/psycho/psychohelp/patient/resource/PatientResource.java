package com.psycho.psychohelp.patient.resource;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PatientResource {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Number phone;
    private Date date;
    private String gender;
    private String image;
}
