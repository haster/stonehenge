package nl.crashdata.stonehenge.rest.controller;

import nl.crashdata.stonehenge.data.entity.PArtist;
import nl.crashdata.stonehenge.data.repository.ArtistRepository;
import nl.crashdata.stonehenge.rest.entity.Artist;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@RestController("/artist")
public class ArtistController {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private ModelMapper mapper;

    @GetMapping("/{id}")
    public Artist get(@PathVariable("id") Long id) {
        return convertToRest(artistRepository.getOne(id));
    }


    private Artist convertToRest(PArtist artist) {
        return mapper.map(artist, Artist.class);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public void handleEntityNotFound() {

    }
}
