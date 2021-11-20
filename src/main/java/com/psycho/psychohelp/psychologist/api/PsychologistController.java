package com.psycho.psychohelp.psychologist.api;

import com.psycho.psychohelp.psychologist.domain.service.PsychologistService;
import com.psycho.psychohelp.psychologist.mapping.PsychologistMapper;
import com.psycho.psychohelp.psychologist.resource.CreatePsychologistResource;
import com.psycho.psychohelp.psychologist.resource.PsychologistResource;
import com.psycho.psychohelp.psychologist.resource.UpdatePsychologistResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Psychologist")
@RestController
@RequestMapping("/api/v1/psychologists")
public class PsychologistController {

    @Autowired
    private PsychologistService psychologistService;

    @Autowired
    private PsychologistMapper mapper;

    @Operation(summary = "Get Psychologists", description = "Get All Psychologists")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Psychologists found"),
            @ApiResponse(responseCode = "400",description = "Psychologist not found")})
    @GetMapping
    public Page<PsychologistResource> getAllPsychologists(Pageable pageable) {
        return mapper.modelListToPage(psychologistService.getAll(), pageable);
    }

    @Operation(summary = "Get Psychologists by Id", description = "Get Psychologist by Id")
    @GetMapping("{psychologistId}")
    public PsychologistResource getById(@PathVariable Long psychologistId)
    {
        return mapper.toResource(psychologistService.getById(psychologistId));
    }

    @Operation(summary = "Create Psychologist", description = "Create Psychologist")
    @PostMapping
    public PsychologistResource createPsychologist(@RequestBody CreatePsychologistResource request)
    {
        return mapper.toResource(psychologistService.create(mapper.toModel(request)));
    }

    @Operation(summary = "Update Psychologist", description = "Update Psychologist by Id")
    @PutMapping("{psychologistId}")
    public PsychologistResource updatePsychologist(@PathVariable Long psychologistId, @RequestBody UpdatePsychologistResource request)
    {
        return mapper.toResource(psychologistService.update(psychologistId, mapper.toModel(request)));
    }

    @Operation(summary = "Delete psychologist", description = "Delete Psychologist by Id")
    @DeleteMapping("{psychologistId}")
    public ResponseEntity<?> deletePsychologist(@PathVariable Long psychologistId)
    {
        return psychologistService.delete(psychologistId);
    }


}
