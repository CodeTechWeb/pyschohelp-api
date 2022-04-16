package com.psycho.psychohelp.psychologist.mapping;

import com.psycho.psychohelp.psychologist.domain.model.entity.Schedule;
import com.psycho.psychohelp.psychologist.resource.*;
import com.psycho.psychohelp.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class ScheduleMapper {

    @Autowired
    private EnhancedModelMapper mapper;

    public ScheduleResource toResource(Schedule model)
    {
        return mapper.map(model, ScheduleResource.class);
    }

    public List<ScheduleResource> toResource(List<Schedule> model)
    {
        return mapper.mapList(model, ScheduleResource.class);
    }

    public Page<ScheduleResource> modelListToPage(List<Schedule> modelList, Pageable pageable)
    {
        return new PageImpl<>(
                mapper.mapList(modelList, ScheduleResource.class),
                pageable,
                modelList.size());
    }

    public Schedule toModel(CreateScheduleResource resource)
    {
        return mapper.map(resource, Schedule.class);
    }

    public Schedule toModel(UpdateScheduleResource resource)
    {
        return mapper.map(resource, Schedule.class);
    }
}
