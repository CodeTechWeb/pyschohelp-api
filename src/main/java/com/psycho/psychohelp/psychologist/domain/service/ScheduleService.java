package com.psycho.psychohelp.psychologist.domain.service;

import com.psycho.psychohelp.psychologist.domain.model.entity.Schedule;

import java.util.List;

public interface ScheduleService {
    List<Schedule> getAll();
    Schedule create(Schedule schedule);
    Schedule getById(Long scheduleId);
}
