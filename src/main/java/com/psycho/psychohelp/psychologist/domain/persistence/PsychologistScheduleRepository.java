package com.psycho.psychohelp.psychologist.domain.persistence;

import com.psycho.psychohelp.psychologist.domain.model.entity.PsychologistSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PsychologistScheduleRepository extends JpaRepository<PsychologistSchedule, Long> {
}
