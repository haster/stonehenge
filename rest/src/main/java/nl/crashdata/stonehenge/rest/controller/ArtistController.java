package nl.crashdata.stonehenge.rest.controller;

import nl.crashdata.stonehenge.data.entity.PArtist;
import nl.crashdata.stonehenge.data.repository.ArtistRepository;
import nl.crashdata.stonehenge.rest.entity.Artist;
import org.apache.catalina.connector.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.net.http.HttpResponse;
import java.util.List;

@RestController
@RequestMapping("/artist")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public Artist get(@PathVariable("id") Long id) {
        return convertToRest(artistRepository.getOne(id));
    }

    @PostMapping
    public ResponseEntity<Artist> create(@RequestBody Artist artist) {
        PArtist newArtist = artistRepository.save(convertToDb(artist));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newArtist.getId()).toUri();

        return ResponseEntity.created(location).body(convertToRest(newArtist));
    }

    private Artist convertToRest(PArtist artist) {
        return mapper.map(artist, Artist.class);
    }

    private PArtist convertToDb(Artist artist) {
        return mapper.map(artist, PArtist.class);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public void handleEntityNotFound() {}
}
