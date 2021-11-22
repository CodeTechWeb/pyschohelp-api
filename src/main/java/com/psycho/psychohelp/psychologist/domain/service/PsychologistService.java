package com.psycho.psychohelp.psychologist.domain.service;

import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PsychologistService {
    List<Psychologist> getAll();
    //Page<Psychologist> getAll(Pageable pageable);
    Psychologist getById(Long psychologistId);
    Psychologist create(Psychologist request);
    Psychologist update(Long psychologistId, Psychologist request);
    ResponseEntity<?> delete(Long psychologistId);
    Psychologist getByEmail(String email);
    List<Psychologist>getByGenre(String genre);
    List<Psychologist>getBySessionType(String sessionType);
    Psychologist getByName(String name);
    List<Psychologist> getByGenreAndSessionType(String genre, String sessionType);
}
