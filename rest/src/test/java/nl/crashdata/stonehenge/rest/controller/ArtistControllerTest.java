package nl.crashdata.stonehenge.rest.controller;

import nl.crashdata.stonehenge.rest.entity.Artist;
import org.junit.Test;
import org.springframework.http.ResponseEntity;

import java.net.URI;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NOT_FOUND;
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

    @Test
    public void createAndGetNewArtist() {
        Artist theBoss = new Artist();
        theBoss.setName("Bruce Springsteen");
        ResponseEntity<Artist> response = template.postForEntity(path("artist"),
                theBoss, Artist.class);
        assertThat(response.getStatusCode(), equalTo(CREATED));
        Artist artist = response.getBody();
        assertThat(artist.getName(), equalTo("Bruce Springsteen"));
        URI newArtistLocation = response.getHeaders().getLocation();
        ResponseEntity<Artist> response2 = template.getForEntity(newArtistLocation,
                Artist.class);
        assertThat(response2.getStatusCode(), equalTo(OK));
        Artist artist2 = response.getBody();
        assertThat(artist2.getName(), equalTo("Bruce Springsteen"));
    }


    @Test
    public void deleteYngwieMalmsteen() {
        ResponseEntity<Artist> response = template.getForEntity(path("artist/535"),
                Artist.class);
        assertThat(response.getStatusCode(), equalTo(OK));
        Artist artist = response.getBody();
        assertThat(artist.getName(), equalTo("Yngwie Malmsteen's Rising Force"));

        template.delete(path("artist/535"));

        ResponseEntity<Artist> response2 = template.getForEntity(path("artist/535"),
                Artist.class);
        assertThat(response2.getStatusCode(), equalTo(NOT_FOUND));
    }

    @Test
    public void updateWAR() {
        ResponseEntity<Artist> response = template.getForEntity(path("artist/517"),
                Artist.class);
        assertThat(response.getStatusCode(), equalTo(OK));
        Artist artist = response.getBody();
        assertThat(artist.getName(), equalTo("WAR"));

        artist.setName("WAR (what is it good for)");
        template.put(path("artist/517"), artist);

        ResponseEntity<Artist> response2 = template.getForEntity(path("artist/517"),
                Artist.class);
        assertThat(response2.getStatusCode(), equalTo(OK));
        Artist artist2 = response2.getBody();
        assertThat(artist2.getName(), equalTo("WAR (what is it good for)"));
    }
}
