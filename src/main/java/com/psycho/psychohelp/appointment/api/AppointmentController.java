package com.psycho.psychohelp.appointment.api;

import com.psycho.psychohelp.appointment.domain.service.AppointmentService;
import com.psycho.psychohelp.appointment.mapping.AppointmentMapper;
import com.psycho.psychohelp.appointment.resource.AppointmentResource;
import com.psycho.psychohelp.appointment.resource.CreateAppointmentResource;
import com.psycho.psychohelp.appointment.resource.UpdateAppointmentResource;
import com.psycho.psychohelp.patient.mapping.PatientMapper;
import com.psycho.psychohelp.patient.resource.PatientResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Appointment")
@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private AppointmentMapper mapper;

    @Autowired
    private PatientMapper patientMapper;

    @Operation(summary = "Get all appointments", description = "Get All Posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments found"),
            @ApiResponse(responseCode = "400", description = "Appointments not found")})
    @GetMapping
    public List<AppointmentResource> getAllAppointments() {
        return mapper.toResource(appointmentService.getAll());
    }

    @Operation(summary = "Get Appointment by Id", description = "Get Appointment by Id")
    @GetMapping("{appointmentId}")
    public AppointmentResource getAppointmentById(@PathVariable Long appointmentId) {
        return mapper.toResource(appointmentService.getById(appointmentId));
    }

    @Operation(summary = "Get all appointments by patientId", description = "Get all appointments by patientId")
    @GetMapping("/patient/{patientId}")
    public List<AppointmentResource> getAllAppointmentsByPatientId(@PathVariable Long patientId) {
        return mapper.toResource(appointmentService.getByPatientId(patientId));
    }

    @Operation(summary = "Get all appointments by psychologistId", description = "Get all appointments by psychologistId")
    @GetMapping("/psychologist/{psychologistId}")
    public List<AppointmentResource> getAllAppointmentsByPsychologistId(@PathVariable Long psychologistId) {
        return mapper.toResource(appointmentService.getByPsychologistId(psychologistId));
    }

    // get patients by psychologistId
    @Operation(summary = "Get all patients by psychologistId", description = "Get all patients by psychologistId")
    @GetMapping("/psychologist/{psychologistId}/patient")
    public List<PatientResource> getAllPatientsByPsychologistId(@PathVariable Long psychologistId) {
        return patientMapper.toResource(appointmentService.getPatientsByPsychologistId(psychologistId));
    }


    @Operation(summary = "Get all appointments by psychologistId and patientId", description = "Get all appointments by psychologistId and patientId")
    @GetMapping("/psychologist/{psychologistId}/patient/{patientId}")
    public List<AppointmentResource> getAllAppointmentsByPsychologistIdAndPatientId(@PathVariable Long psychologistId, @PathVariable Long patientId) {
        return mapper.toResource(appointmentService.getByPatientIdAndPsychologistId(patientId, psychologistId));
    }

    @Operation(summary = "Create appointment", description = "Create appointment")
    @PostMapping("/patient/{patientId}/psychologist/{psychologistId}")
    public AppointmentResource createAppointment(@PathVariable(name = "patientId") Long patientId,
                                                 @PathVariable(name = "psychologistId") Long psychologistId, @Valid @RequestBody CreateAppointmentResource request) {
        return mapper.toResource(appointmentService.create(mapper.toModel(request), psychologistId, patientId));
    }

    @Operation(summary = "Update appointment", description = "Update appointment by Id ")
    @PutMapping("{appointmentId}")
    public AppointmentResource updateAppointment(@PathVariable Long appointmentId, @Valid @RequestBody UpdateAppointmentResource request) {
        return mapper.toResource(appointmentService.update(appointmentId, mapper.toModel(request)));
    }

    @Operation(summary = "Delete appointment", description = "Delete appointment by Id")
    @DeleteMapping("{appointmentId}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long appointmentId) {
        return appointmentService.delete(appointmentId);
    }
}
