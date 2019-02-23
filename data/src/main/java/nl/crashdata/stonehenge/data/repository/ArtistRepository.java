package nl.crashdata.stonehenge.data.repository;

import nl.crashdata.stonehenge.data.entity.PArtist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<PArtist, Long> {
}
