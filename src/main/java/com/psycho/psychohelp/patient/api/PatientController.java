package com.psycho.psychohelp.patient.api;

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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public PatientResource createPatient(@RequestBody CreatePatientResource request)
    {
        Patient patient = patientService.create(mapper.toModel(request));
        CreateLogBookResource resource = new CreateLogBookResource();
        mapperLog.toResource(logBookService.create(patient.getId() ,mapperLog.toModel(resource)));
        PatientResource patientResponse = mapper.toResource(mapper.toModel(request));
        patientResponse.setId(patient.getId());
        return patientResponse;
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
