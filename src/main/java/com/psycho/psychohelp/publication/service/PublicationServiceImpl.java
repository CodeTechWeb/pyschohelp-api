package com.psycho.psychohelp.publication.service;

import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import com.psycho.psychohelp.psychologist.domain.persistence.PsychologistRepository;
import com.psycho.psychohelp.publication.domain.model.entity.Publication;
import com.psycho.psychohelp.publication.domain.persistence.PublicationRepository;
import com.psycho.psychohelp.publication.domain.service.PublicationService;
import com.psycho.psychohelp.shared.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PublicationServiceImpl implements PublicationService {

    private final static String ENTITY = "Publication";

    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private PsychologistRepository psychologistRepository;

    @Override
    public List<Publication> getAll() {
        return publicationRepository.findAll();
    }

    //@Override
    //public Page<Publication> getAll(Pageable pageable) {
    //    return publicationRepository.findAll(pageable);
    //}

    @Override
    public Publication getById(Long publicationId) {
        return publicationRepository.findById(publicationId)
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, publicationId));
    }

    @Override
    public List<Publication> getByPsychologistId(Long psychologistId) {
        return publicationRepository.findByPsychologistId(psychologistId);
    }

    @Override
    public Publication create(Publication publication, Long psychologistId) {
        Psychologist psychologist = psychologistRepository.findById(psychologistId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with Id " + psychologistId));
        publication.setPsychologist(psychologist);
        return publicationRepository.save(publication);
    }

    @Override
    public Publication update(Long publicationId, Publication request) {
        return publicationRepository.findById(publicationId).map(publication ->
                        publicationRepository.save(publication
                                .withTitle(request.getTitle())
                                .withDescription(request.getDescription())
                                .withContent(request.getContent())))
                .orElseThrow(()-> new ResourceNotFoundException(ENTITY, publicationId));
    }

    @Override
    public ResponseEntity<?> delete(Long publicationId) {
        return publicationRepository.findById(publicationId).map(publication -> {
            publicationRepository.delete(publication);
            return ResponseEntity.ok().build();
        }).orElseThrow(()-> new ResourceNotFoundException(ENTITY, publicationId));
    }
}
