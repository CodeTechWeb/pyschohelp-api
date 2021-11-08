package com.psycho.psychohelp.patient.domain.persistence;

import com.psycho.psychohelp.patient.domain.model.entity.LogBook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogBookRepository extends JpaRepository<LogBook, Long> {

    List<LogBook> findByPatientId(Long postId);
    Page<LogBook> findByPatientId(Long postId, Pageable pageable);
}
