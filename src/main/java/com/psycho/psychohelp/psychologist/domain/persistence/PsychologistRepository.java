package com.psycho.psychohelp.psychologist.domain.persistence;

import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {
}
