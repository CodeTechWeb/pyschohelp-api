package com.psycho.psychohelp.psychologist.domain.model.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="psychologist_schedule")
@NoArgsConstructor
@AllArgsConstructor
public class PsychologistSchedule {

    @EmbeddedId
    private PsychologistScheduleFK psychologistScheduleFK;

    @ManyToOne
    @MapsId("psychologistId")
    private Psychologist psychologist;

    @ManyToOne
    @MapsId("scheduleId")
    private Schedule schedule;
}
