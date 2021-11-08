package com.psycho.psychohelp.patient.resource;

import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LogBookResource {

    private Long id;
    private String logbookName;
    private String publicHistory;
    private String consultationReason;
}
