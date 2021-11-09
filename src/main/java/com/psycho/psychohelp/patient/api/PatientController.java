package com.psycho.psychohelp.patient.api;

import com.psycho.psychohelp.patient.domain.model.entity.LogBook;
import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.patient.domain.service.LogBookService;
import com.psycho.psychohelp.patient.domain.service.PatientService;
import com.psycho.psychohelp.patient.mapping.LogBookMapper;
import com.psycho.psychohelp.patient.mapping.PatientMapper;
import com.psycho.psychohelp.patient.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.HttpRetryException;
import java.util.List;

@Tag(name = "Patient")
@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {

    @Autowired
    LogBookService logBookService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientMapper mapper;

    @Autowired
    private LogBookMapper mapperLog;

    @Operation(summary = "Get Patients", description = "Get All Patients")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Patients found"),
            @ApiResponse(responseCode = "400",description = "Patient not found") })
    @GetMapping
    public List<PatientResource> getAllPatients() {
        return mapper.toResource(patientService.getAll());
    }

    @Operation(summary = "Get Patients by Id", description = "Get Patient by Id")
    @GetMapping("{patientId}")
    public PatientResource getById(@PathVariable Long patientId) {
        return mapper.toResource(patientService.getById(patientId));
    }


    @Operation(summary = "Get Patients by Email", description = "Get Patient information by email")
    @GetMapping("/email/{patientEmail}")
    public PatientResource getPatientByEmail(@PathVariable String patientEmail) {
        return mapper.toResource(patientService.getByEmail(patientEmail));
    }

    @Operation(summary = "Create patient", description = "Create Patient")
    @PostMapping
    public ResponseEntity<PatientResource> createPatient(@Validated @RequestBody CreatePatientResource request )
    {
        CreateLogBookResource resource = new CreateLogBookResource();
        try
        {
            PatientResource patientResponse = mapper.toResource(mapper.toModel(request));
            LogBook logBook = logBookService.create(mapperLog.toModel(resource), mapper.toModel(request));
            Patient patient = patientService.create(mapper.toModel(patientResponse), logBook.getId());
            patientResponse.setId(patient.getId());
            return new ResponseEntity<PatientResource>(patientResponse, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<PatientResource>(HttpStatus.BAD_REQUEST);
        }
    }

    @Operation(summary = "Update patient", description = "Update Patient by Id ")
    @PutMapping("{patientId}")
    public PatientResource updatePatient(@PathVariable Long patientId, @RequestBody UpdatePatientResource request) {
        return mapper.toResource(patientService.update(patientId, mapper.toModel(request)));
    }

    @Operation(summary = "Delete patient", description = "Delete Patient by Id")
    @DeleteMapping("{patientId}")
    public ResponseEntity<?> deletePost(@PathVariable Long patientId) {
        return patientService.delete(patientId);
    }
}
