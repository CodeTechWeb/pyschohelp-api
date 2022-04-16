package com.psycho.psychohelp.psychologist.domain.persistence;

import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PsychologistRepository extends JpaRepository<Psychologist, Long> {
    Psychologist findByEmail(String email);
    List<Psychologist> findByGenre(String genre);
    List<Psychologist> findBySessionType(String sessionType);
    Psychologist findByName(String name);
    List<Psychologist> findByGenreAndSessionType(String genre, String sessionType);
}

