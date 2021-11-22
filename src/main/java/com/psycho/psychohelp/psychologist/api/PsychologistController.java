package com.psycho.psychohelp.psychologist.api;

import com.psycho.psychohelp.psychologist.domain.service.PsychologistService;
import com.psycho.psychohelp.psychologist.mapping.PsychologistMapper;
import com.psycho.psychohelp.psychologist.resource.CreatePsychologistResource;
import com.psycho.psychohelp.psychologist.resource.PsychologistResource;
import com.psycho.psychohelp.psychologist.resource.UpdatePsychologistResource;
import com.psycho.psychohelp.shared.exception.ResourceNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    public List<PsychologistResource> getAllPsychologists() {
        return mapper.toResource(psychologistService.getAll());
    }

    @Operation(summary = "Get Psychologists by Id", description = "Get Psychologist by Id")
    @GetMapping("{psychologistId}")
    public PsychologistResource getById(@PathVariable Long psychologistId)
    {
        return mapper.toResource(psychologistService.getById(psychologistId));
    }

    @Operation(summary = "Get Psychologists by Email", description = "Get Psychologist by Email")
    @GetMapping("email/{psychologistEmail}")
    public PsychologistResource getByEmail(@PathVariable String psychologistEmail)
    {
        return mapper.toResource(psychologistService.getByEmail(psychologistEmail));
    }

    @Operation(summary = "Get Psychologists by Genre", description = "Get Psychologist by Genre")
    @GetMapping("genre/{psychoGenre}")
    public List<PsychologistResource> getPsychologistByGenre(@PathVariable String psychoGenre)
    {
        return mapper.toResource(psychologistService.getByGenre(psychoGenre));
    }

    @Operation(summary = "Get Psychologists by session type", description = "Get Psychologist by session type")
    @GetMapping("sessionType/{sessionType}")
    public List<PsychologistResource> getPsychologistsBySessionType(@PathVariable String sessionType)
    {
        return mapper.toResource(psychologistService.getBySessionType(sessionType));
    }

    @Operation(summary = "Get Psychologists by name", description = "Get Psychologist by name")
    @GetMapping("name/{name}")
    public PsychologistResource getPsychologistByName(@PathVariable String name)
    {
        return mapper.toResource(psychologistService.getByName(name));
    }

    @Operation(summary = "Get Psychologists by genre and session type", description = "Get Psychologist by genre and session type")
    @GetMapping("genre/{genre}&sessionType/{sessionType}")
    public List<PsychologistResource> getPsychologistByGenreAndSessionType(@PathVariable String genre, @PathVariable String sessionType)
    {
        return mapper.toResource(psychologistService.getByGenreAndSessionType(genre,sessionType));
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
