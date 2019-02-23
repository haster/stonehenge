package nl.crashdata.stonehenge.rest.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpStatus.OK;

public class StatusControllerTest extends AbstractRestControllerTest {

    @Test
    public void getStatus() {
        ResponseEntity<String> response = template.getForEntity(path("status"),
                String.class);
        assertThat(response.getStatusCode(), equalTo(OK));
        assertThat(response.getBody(), equalTo("Online"));
    }
}
