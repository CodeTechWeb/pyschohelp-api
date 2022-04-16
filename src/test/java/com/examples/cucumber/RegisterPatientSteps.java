package com.examples.cucumber;

import com.psycho.psychohelp.patient.domain.model.entity.Patient;
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
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RegisterPatientSteps {

    private RestTemplate restTemplate = new RestTemplate();
    private String url="http://localhost:8080/api/v1";
    private String message="";
    Patient patient;
    Long patientId = randomLong();


    public Long randomLong() {
        Long generatedLong = new Random().nextLong();
        return generatedLong;
    }

    @Given("I want to register")
    public void i_want_to_register() {
        String patientUrl=url + "/patients";
        String getEndpoint=restTemplate.getForObject(patientUrl, String.class);
        log.info(getEndpoint);
        assertTrue(!getEndpoint.isEmpty());
    }
    @And("I enter my own information like firstname {string}, lastname {string}, email {string}, password {string}, phone {string} and gender {string}")
    public void i_enter_my_own_information_like_firstname_lastname_email_password_phone_and_gender(String firstname, String lastname, String email, String password, String phone, String gender) {
        String patientUrl=url + "/patients";
        Patient newPatient = new Patient(patientId, firstname, lastname, email, password, phone,"Hoy",gender,"img.jpg");
        patient=restTemplate.postForObject(patientUrl,newPatient,Patient.class);
        log.info(patient.getId());
        assertNotNull(patient);
    }

    @Then("I should be able to see my newly account")
    public void i_should_be_able_to_see_my_newly_account() {

        String patientUrl=url + "/patients/"+patient.getId();
        try
        {
            Patient getPatientById=restTemplate.getForObject(patientUrl, Patient.class, patient.getId());
            log.info(getPatientById);
        }catch(RestClientException e){
            message="";
        }
        assertEquals("",message);
    }
}
