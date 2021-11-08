package com.psycho.psychohelp.patient.mapping;

import com.psycho.psychohelp.patient.domain.model.entity.LogBook;
import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.patient.resource.*;
import com.psycho.psychohelp.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class LogBookMapper {

    @Autowired
    private EnhancedModelMapper mapper;

    // Object Mapping

    public Page<LogBookResource> modelListToPage(List<LogBook> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, LogBookResource.class),
                pageable,
                modelList.size());
    }

    public LogBookResource toResource(LogBook model) { return mapper.map(model, LogBookResource.class); }

    public List<LogBookResource> toResource(List<LogBook> model) { return mapper.mapList(model, LogBookResource.class); }

    public LogBook toModel(CreateLogBookResource resource) { return mapper.map(resource, LogBook.class); }

    public LogBook toModel(UpdateLogBookResource resource) { return mapper.map(resource, LogBook.class); }
}
