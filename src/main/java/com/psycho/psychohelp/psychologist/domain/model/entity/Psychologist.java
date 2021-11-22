package com.psycho.psychohelp.psychologist.domain.model.entity;

import com.psycho.psychohelp.shared.domain.model.AuditModel;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name = "psychologist")
public class Psychologist extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private Date birthdayDate;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Email
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

    @OneToMany(mappedBy = "psychologist")
    private List<PsychologistSchedule> psychologistSchedules;
}
