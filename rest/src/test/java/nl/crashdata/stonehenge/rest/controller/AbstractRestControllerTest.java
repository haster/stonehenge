package nl.crashdata.stonehenge.rest.controller;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AbstractRestControllerTest {
    protected URL base;

    @Autowired
    protected TestRestTemplate template;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }

    protected String path(String path) {
        try {
            return base.toURI().resolve(path).toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }


}
