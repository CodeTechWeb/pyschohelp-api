package com.psycho.psychohelp.psychologist.service;

import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import com.psycho.psychohelp.psychologist.domain.persistence.PsychologistRepository;
import com.psycho.psychohelp.psychologist.domain.service.PsychologistService;
import com.psycho.psychohelp.shared.exception.ResourceNotFoundException;
import com.psycho.psychohelp.shared.exception.ResourceValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Service
public class PsychologistServiceImpl implements PsychologistService {

    private final static String ENTITY = "Psychologist";

    @Autowired
    private PsychologistRepository psychologistRepository;

    @Autowired
    private Validator validator;


    @Override
    public List<Psychologist> getAll()
    {
        return psychologistRepository.findAll();
    }

    @Override
    public Page<Psychologist> getAll(Pageable pageable)
    {
        return psychologistRepository.findAll(pageable);
    }

    @Override
    public Psychologist getById(Long psychologistId) {
        return psychologistRepository.findById(psychologistId)
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, psychologistId));
    }

    @Override
    public Psychologist create(Psychologist request) {
        Set<ConstraintViolation<Psychologist>> violations = validator.validate(request);

        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);
        return psychologistRepository.save(request);
    }

    @Override
    public Psychologist update(Long psychologistId, Psychologist request) {
        Set<ConstraintViolation<Psychologist>> violations = validator.validate(request);
        if(!violations.isEmpty())
            throw new ResourceValidationException(ENTITY, violations);

        return psychologistRepository.findById(psychologistId).map(psychologist ->
                psychologistRepository.save(
                        psychologist.withName(request.getName())
                                .withDni(request.getDni())
                                .withBirthdayDate(request.getBirthdayDate())
                                .withEmail(request.getEmail())
                                .withPassword(request.getPassword())
                                .withPhone(request.getPhone())
                                .withSpecialization(request.getSpecialization())
                                .withFormation(request.getFormation())
                                .withAbout(request.getAbout())
                                .withGenre(request.getGenre())
                                .withSessionType(request.getSessionType())
                                .withImage(request.getImage())
                                .withCmp(request.getCmp())
                                .withActive(request.getActive())
                                .withFresh(request.getFresh())))
                .orElseThrow(() -> new ResourceNotFoundException(ENTITY, psychologistId));
    }

    @Override
    public ResponseEntity<?> delete(Long psychologistId) {

        return psychologistRepository.findById(psychologistId).map(psychologist -> {
            psychologistRepository.delete(psychologist);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(ENTITY, psychologistId));
    }
}
