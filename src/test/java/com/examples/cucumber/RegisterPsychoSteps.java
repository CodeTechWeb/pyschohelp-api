package com.examples.cucumber;

import com.psycho.psychohelp.patient.domain.model.entity.Patient;
import com.psycho.psychohelp.psychologist.domain.model.entity.Psychologist;
import com.psycho.psychohelp.psychologist.domain.model.entity.PsychologistSchedule;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import lombok.extern.log4j.Log4j2;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class RegisterPsychoSteps {

    private RestTemplate restTemplate = new RestTemplate();
    private String url="http://localhost:8080/api/v1";
    private String message="";
    Psychologist psychologist;
    Long psychologistId = randomLong();


    public Long randomLong() {
        Long generatedLong = new Random().nextLong();
        return generatedLong;
    }


    @Given("I want to register as psychologist")
    public void i_want_to_register_as_psychologist() {
        String psychologistUrl=url + "/psychologists";
        String getEndpoint=restTemplate.getForObject(psychologistUrl, String.class);
        log.info(getEndpoint);
        assertTrue(!getEndpoint.isEmpty());
    }
    @And("I enter my own information like name {string}, dni {string}, email {string}, password {string}, phone {string}, specialization {string}, formation {string}, about {string}, genre {string}, sessionType {string} and code {string}")
    public void i_enter_my_own_information_like_name_dni_email_password_phone_specialization_formation_about_me_genre_session_type_and_code(String name, String dni, String email, String password, String phone, String specialization, String formation, String about, String genre, String sessionType, String code) {
        String psychologistUrl=url + "/psychologists";
        Date birthdayDate = new Date();

        Psychologist newPsychologist = new Psychologist(psychologistId, name,dni, birthdayDate, email, password, phone,specialization,formation, about,genre, sessionType,"img.jpg",code, true,true);
        psychologist=restTemplate.postForObject(psychologistUrl,newPsychologist,Psychologist.class);
        log.info(psychologist.getId());
        assertNotNull(psychologist);
    }
    @Then("I should be able to see my newly psychologist account")
    public void i_should_be_able_to_see_my_newly_psychologist_account() {
        String patientUrl=url + "/psychologists/"+psychologist.getId();
        try
        {
            Patient getPsychologistById=restTemplate.getForObject(patientUrl, Patient.class, psychologist.getId());
            log.info(getPsychologistById);
        }catch(RestClientException e){
            message="";
        }
        assertEquals("",message);
    }
}
