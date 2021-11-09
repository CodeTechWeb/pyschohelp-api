package com.psycho.psychohelp.appointment.api;

import com.psycho.psychohelp.appointment.domain.service.AppointmentService;
import com.psycho.psychohelp.appointment.mapping.AppointmentMapper;
import com.psycho.psychohelp.appointment.resource.AppointmentResource;
import com.psycho.psychohelp.appointment.resource.CreateAppointmentResource;
import com.psycho.psychohelp.appointment.resource.UpdateAppointmentResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "Appointment")
@RestController
@RequestMapping("/api/v1/appointment")
public class AppointmentController {

    private final AppointmentService appointmentService;

    private final AppointmentMapper mapper;

    public AppointmentController(AppointmentService appointmentService, AppointmentMapper mapper) {
        this.appointmentService = appointmentService;
        this.mapper = mapper;
    }

    @Operation(summary = "Get all appointments", description = "Get All Posts")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Appointments found"),
            @ApiResponse(responseCode = "400", description = "Appointments not found")})
    @GetMapping
    public Page<AppointmentResource> getAllAppointments(Pageable pageable) {
        return mapper.modelListToPage(appointmentService.getAll(), pageable);
    }

    @Operation(summary = "Get Appointment by Id", description = "Get Appointment by Id")
    @GetMapping("{appointmentId}")
    public AppointmentResource getAppointmentById(@PathVariable Long appointmentId) {
        return mapper.toResource(appointmentService.getById(appointmentId));
    }

    //@Operation(summary = "Get Appointment by Psychologist Name", description = "Get Appointment by Psychologist Name")
    //@GetMapping("{psychoName}")
    //public AppointmentResource getAppointmentByPsychologistName(@PathVariable String psychoName) {
    //    return mapper.toResource(appointmentService.getByPsychologistName(psychoName));
    //}

    @Operation(summary = "Get Appointment by Notes", description = "Get Appointment by Notes")
    @GetMapping("{appointmentNotes}")
    public AppointmentResource getAppointmentByPsychoNotes(@PathVariable String appointmentNotes) {
        return mapper.toResource(appointmentService.getByPsychoNotes(appointmentNotes));
    }

    @Operation(summary = "Create appointment", description = "Create appointment")
    public AppointmentResource createAppointment(@Valid @RequestBody CreateAppointmentResource request) {
        return mapper.toResource(appointmentService.create(mapper.toModel(request)));
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
