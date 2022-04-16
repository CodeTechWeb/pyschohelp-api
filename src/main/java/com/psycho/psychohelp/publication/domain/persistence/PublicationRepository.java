package com.psycho.psychohelp.publication.domain.persistence;

import com.psycho.psychohelp.publication.domain.model.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> findByPsychologistId(Long psychologistId);
}
