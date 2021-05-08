package pl.lukakan.showstracker.show.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukakan.showstracker.show.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);
}
