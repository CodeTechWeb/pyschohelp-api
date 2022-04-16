package com.psycho.psychohelp.patient.domain.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.psycho.psychohelp.shared.domain.model.AuditModel;
import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@With
@Entity
@Table(name ="patients")
public class Patient extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String firstName;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String lastName;

    @NotNull
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotNull
    @NotBlank
    @Size(min=8, max = 20) //check
    private String password;

    @NotNull
    @NotBlank
    @Size(max = 9) //check
    private String phone;

    @NotNull
    private String date;

    @NotNull
    @NotBlank
    @Size(max = 20)
    private String gender;

    @NotNull
    @NotBlank
    @Size(max = 200)
    private String img;


}
