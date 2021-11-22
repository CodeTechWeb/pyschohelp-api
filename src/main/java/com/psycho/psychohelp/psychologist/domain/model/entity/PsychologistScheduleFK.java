package com.psycho.psychohelp.psychologist.domain.model.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PsychologistScheduleFK implements Serializable {

    @Column(name = "psychologist_id", nullable = false)
    private Long psychologistId;

    @Column(name = "schedule_id", nullable = false)
    private Long scheduleId;
}
