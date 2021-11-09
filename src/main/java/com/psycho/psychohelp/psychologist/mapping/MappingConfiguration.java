package com.psycho.psychohelp.psychologist.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("psychologistMappingConfiguration")
public class MappingConfiguration {
    @Bean
    public PsychologistMapper psychologistMapper() {
        return new PsychologistMapper();
    }
}
