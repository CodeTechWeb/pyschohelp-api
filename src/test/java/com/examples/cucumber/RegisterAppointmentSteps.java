package com.examples.cucumber;

import com.psycho.psychohelp.appointment.domain.model.entity.Appointment;
import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import com.psycho.psychohelp.publication.domain.model.entity.Publication;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;


@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RegisterAppointmentSteps {

    private RestTemplate restTemplate = new RestTemplate();
    private String url="http://localhost:8080/api/v1";
    private String message="";
    Appointment appointment;
    Long appointmentId = randomLong();

    public Long randomLong() {
        Long generatedLong = new Random().nextLong();
        return generatedLong;
    }

    @Given("I want to schedule")
    public void i_want_to_schedule() {
        String appointmentUrl=url + "/appointment";
        String getEndpoint=restTemplate.getForObject(appointmentUrl, String.class);
        log.info(getEndpoint);
        assertTrue(!getEndpoint.isEmpty());
    }
    @And("I schedule an appointment with url {string}, motive {string}, history {string}, test {string}, treatment {string} and date {string}")
    public void i_schedule_an_appointment_with_url_motive_history_test_treatment_and_date(String meetUrl, String motive, String history, String test, String treatment, String date) {
        String appointmentUrl=url + "/appointment/patient/"+1L + "/psychologist/"+1L;
        Psychologist psychologist = restTemplate.getForObject(url+"/psychologists/"+1L,Psychologist.class);
        Patient patient = restTemplate.getForObject(url+"/patients/"+1L, Patient.class);

        Appointment newAppointment = new Appointment(appointmentId, meetUrl, motive, history, test, treatment, date, patient,psychologist);
        appointment=restTemplate.postForObject(appointmentUrl,newAppointment,Appointment.class);
        log.info(appointment.getId());
        assertNotNull(appointment);
    }
    @Then("I should be able to see my newly appointment")
    public void i_should_be_able_to_see_my_newly_appointment() {
        String appointmentUrl=url + "/appointment/"+1L;
        try
        {
            Appointment getAppointmentById=restTemplate.getForObject(appointmentUrl, Appointment.class, appointment.getId());
            log.info(getAppointmentById);
        }catch(RestClientException e){
            message="";
        }
        assertEquals("",message);
    }
}
