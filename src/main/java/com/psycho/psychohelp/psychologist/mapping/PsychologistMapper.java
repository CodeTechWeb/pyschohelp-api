package com.psycho.psychohelp.psychologist.mapping;

import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import com.psycho.psychohelp.psychologist.resource.CreatePsychologistResource;
import com.psycho.psychohelp.psychologist.resource.PsychologistResource;
import com.psycho.psychohelp.psychologist.resource.UpdatePsychologistResource;
import com.psycho.psychohelp.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PsychologistMapper {

    @Autowired
    private EnhancedModelMapper mapper;

    // Object Mapping

    public PsychologistResource toResource(Psychologist model)
    {
        return mapper.map(model, PsychologistResource.class);
    }

    public List<PsychologistResource> toResource(List<Psychologist> model)
    {
        return mapper.mapList(model, PsychologistResource.class);
    }

    public Page<PsychologistResource> modelListToPage(List<Psychologist> modelList, Pageable pageable)
    {
        return new PageImpl<>(
                mapper.mapList(modelList, PsychologistResource.class),
                pageable,
                modelList.size());
    }

    public Psychologist toModel(CreatePsychologistResource resource)
    {
        return mapper.map(resource, Psychologist.class);
    }

    public Psychologist toModel(UpdatePsychologistResource resource)
    {
        return mapper.map(resource, Psychologist.class);
    }
}
