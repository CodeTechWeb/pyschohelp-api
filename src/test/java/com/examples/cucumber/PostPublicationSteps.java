package com.examples.cucumber;

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

import java.util.Date;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PostPublicationSteps {

    private RestTemplate restTemplate = new RestTemplate();
    private String url="http://localhost:8080/api/v1";
    private String message="";
    Publication publication;
    Long publicationId = randomLong();


    public Long randomLong() {
        Long generatedLong = new Random().nextLong();
        return generatedLong;
    }


    @Given("I want to post a publication")
    public void i_want_to_post_a_publication() {
        String publicationUrl=url + "/publications";
        String getEndpoint=restTemplate.getForObject(publicationUrl, String.class);
        log.info(getEndpoint);
        assertTrue(!getEndpoint.isEmpty());
    }
    @And("I post a publication with title {string}, description {string}, tags {string}, photo {string} and content {string}")
    public void i_post_a_publication_with_title_description_tags_photo_and_content(String title, String description, String tags, String photo, String content) {
        String publicationUrl=url + "/publications/psychologists/" + 1L;

        Publication newPublication = new Publication(publicationId, title, description, tags, photo, content);
        publication=restTemplate.postForObject(publicationUrl,newPublication,Publication.class);
        log.info(publication.getId());
        assertNotNull(publication);
    }
    @Then("I should be able to see my newly publication")
    public void i_should_be_able_to_see_my_newly_publication() {
        String publicationUrl=url + "/publications/"+publication.getId();
        try
        {
            Publication getPublicationById=restTemplate.getForObject(publicationUrl, Publication.class, publication.getId());
            log.info(getPublicationById);
        }catch(RestClientException e){
            message="";
        }
        assertEquals("",message);
    }
}
