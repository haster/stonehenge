package nl.crashdata.stonehenge.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import nl.crashdata.stonehenge.data.repository.ArtistRepository;
import nl.crashdata.stonehenge.entities.PArtist;
import nl.crashdata.stonehenge.rest.entity.Artist;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class StonehengeStartupListener{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadArtists();
    }

    private void loadArtists() {
        InputStream artistsStream = getClass().getClassLoader().getResourceAsStream("artists.json");
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
        TypeReference<List<Artist>> typeReference = new TypeReference<>(){};
        try {
            List<Artist> artists = mapper.readValue(artistsStream,typeReference);
            artists.forEach(a -> jdbcTemplate.update("insert into artist (id, version, name) values (?,0,?)", a.getId(), a.getName()));
            System.out.println("Artists Saved!");
        } catch (IOException e){
            System.out.println("Unable to save artists: " + e.getMessage());
        }
    }
}
