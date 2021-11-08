package com.psycho.psychohelp.patient.api;

import com.psycho.psychohelp.patient.domain.service.LogBookService;
import com.psycho.psychohelp.patient.mapping.LogBookMapper;
import com.psycho.psychohelp.patient.resource.LogBookResource;
import com.psycho.psychohelp.patient.resource.UpdateLogBookResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "LogBook")
@RestController
@RequestMapping("/api/v1/logbooks")
public class LogBookController {
    @Autowired
    LogBookService logBookService;

    @Autowired
    LogBookMapper mapper;

    @Operation(summary = "Get Logbooks", description = "Get all Logbooks")
    @GetMapping
    public List<LogBookResource> getAll() {
        return mapper.toResource(logBookService.getAll());
    }


    @Operation(summary = "Get Logbook by Id", description = "Get Logbook information by Id")
    @GetMapping({"{logbookId}"})
    public LogBookResource getById(@PathVariable Long logbookId) {
        return mapper.toResource(logBookService.getById(logbookId));
    }

    @Operation(summary = "Update LogBook by Id", description = "Update LogBook information")
    @PutMapping("{logbookId}")
    public LogBookResource updateLogBook(@PathVariable Long logbookId, @RequestBody UpdateLogBookResource request) {
        return mapper.toResource(logBookService.update(logbookId, mapper.toModel(request)));
    }
}
