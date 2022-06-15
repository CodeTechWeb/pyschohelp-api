package com.psycho.psychohelp.psychologist.resource;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class UpdatePsychologistResource {

    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 40)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 9)
    private String dni;

    @NotNull
    @NotBlank
    private Date birthdayDate;

    @NotNull
    @NotBlank
    @Size(max = 50)
    private String email;

    @NotNull
    @NotBlank
    @Size(min=8, max = 20)
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 9)
    private String phone;

    @NotNull
    @NotBlank
    @Size(max=250)
    private String specialization;

    @NotNull
    @NotBlank
    @Size(max=250)
    private String formation;

    @NotNull
    @NotBlank
    @Size(max=250)
    private String about;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String genre;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String sessionType;

    @NotNull
    @NotBlank
    @Size(max=250)
    private String image;

    @NotNull
    @NotBlank
    @Size(max = 8)
    private String cmp;

    @NotNull
    private Boolean active;

    @NotNull
    private Boolean fresh;
}
