package nl.crashdata.stonehenge.rest.controller;

import nl.crashdata.stonehenge.rest.entity.Artist;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpStatus.OK;

public class ArtistControllerTest extends AbstractRestControllerTest {

    @Test
    public void get4NonBlondes() {
        ResponseEntity<Artist> response = template.getForEntity(path("artist/577"),
                Artist.class);
        assertThat(response.getStatusCode(), equalTo(OK));
        Artist artist = response.getBody();
        assertThat(artist.getName(), equalTo("4 Non Blondes"));
    }
}
