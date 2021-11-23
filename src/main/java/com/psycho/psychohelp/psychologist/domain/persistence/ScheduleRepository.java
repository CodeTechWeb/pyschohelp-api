package com.psycho.psychohelp.psychologist.domain.persistence;

import com.psycho.psychohelp.psychologist.domain.model.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    //find list of schedules of a psychologist from the table psychologist_schedule
    //where psychologist_id is the id of the psychologist
    @Query(value = "select * from schedule where id in(Select b.schedule_id From psychologist_schedule b Where b.psychologist_id = :psychologist_id)", nativeQuery = true)
    List<Schedule> findByPsychologistId(@Param("psychologist_id") Long psychologist_id);

}
