package com.psycho.psychohelp.patient.mapping;

import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.patient.resource.CreatePatientResource;
import com.psycho.psychohelp.patient.resource.PatientResource;
import com.psycho.psychohelp.patient.resource.UpdatePatientResource;
import com.psycho.psychohelp.shared.mapping.EnhancedModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PatientMapper {

    @Autowired
    private EnhancedModelMapper mapper;

    // Object Mapping

    public Page<PatientResource> modelListToPage(List<Patient> modelList, Pageable pageable) {
        return new PageImpl<>(
                mapper.mapList(modelList, PatientResource.class),
                pageable,
                modelList.size());
    }

    public PatientResource toResource(Patient model) { return mapper.map(model, PatientResource.class); }

    public List<PatientResource> toResource(List<Patient> model) { return mapper.mapList(model, PatientResource.class); }

    public Patient toModel(CreatePatientResource resource) { return mapper.map(resource, Patient.class); }

    public Patient toModel(PatientResource resource) { return mapper.map(resource, Patient.class); }

    public Patient toModel(UpdatePatientResource resource) { return mapper.map(resource, Patient.class); }
}
