package com.psycho.psychohelp.patient.api;

import com.psycho.psychohelp.patient.domain.service.PatientService;
import com.psycho.psychohelp.patient.mapping.PatientMapper;
import com.psycho.psychohelp.patient.resource.CreatePatientResource;
import com.psycho.psychohelp.patient.resource.PatientResource;
import com.psycho.psychohelp.patient.resource.UpdatePatientResource;
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
    private PatientService patientService;

    @Autowired
    private PatientMapper mapper;

    @Operation(summary = "Get Patients", description = "Get All Posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Patients found"),
            @ApiResponse(responseCode = "400",description = "Patient not found") })
    @GetMapping
    public List<PatientResource> getAllPatients() {
        return mapper.toResource(patientService.getAll());
    }

    @Operation(summary = "Get Patients by Id", description = "Get Patient by Id")
    @GetMapping("{patientId}")
    public PatientResource getPatientById(@PathVariable Long patientId) {
        return mapper.toResource(patientService.getById(patientId));
    }

    @Operation(summary = "Get Patients by Name", description = "Get Patient by First and Last Name")
    @GetMapping("{patientFirstName&patientLastName}")
    public PatientResource getPatientByFirstAndLastName(@PathVariable String patientFirstName, String patientLastName) {
        return mapper.toResource(patientService.getByName(patientFirstName, patientLastName));
    }

    @Operation(summary = "Get Patients by Name", description = "Get Patient by First and Last Name")
    @GetMapping("{patientEmail}")
    public PatientResource getPatientByEmail(@PathVariable String patientEmail) {
        return mapper.toResource(patientService.getByEmail(patientEmail));
    }

    @Operation(summary = "Create patient", description = "Create Patient")
    @PostMapping
    public PatientResource createPatient(@RequestBody CreatePatientResource request)
    {
        return mapper.toResource(patientService.create(mapper.toModel(request)));
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
