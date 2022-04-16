package com.psycho.psychohelp.publication.domain.service;

import com.psycho.psychohelp.publication.domain.model.entity.Publication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PublicationService {
    List<Publication>getAll();
    //Page<Publication> getAll(Pageable pageable);
    Publication getById(Long publicationId);
    Publication create(Publication publication, Long psychologistId);
    Publication update(Long publicationId, Publication request);
    ResponseEntity<?> delete(Long publicationId);
    List<Publication> getByPsychologistId(Long psychologistId);
}
