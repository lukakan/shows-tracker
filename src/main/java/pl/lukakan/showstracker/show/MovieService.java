package pl.lukakan.showstracker.show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukakan.showstracker.cast.role.model.Role;
import pl.lukakan.showstracker.show.model.Genre;
import pl.lukakan.showstracker.show.model.Movie;
import pl.lukakan.showstracker.show.repository.GenreRepository;
import pl.lukakan.showstracker.show.repository.MovieRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
    }

    public Optional<Movie> getById(Long id) {
        return movieRepository.findById(id);
    }

    public void add(Movie movie) {
        movieRepository.save(movie);
    }

    public List<Role> getActors(Movie movie) {
        return movie.getRoles().stream()
                .filter(role -> role.getFunction().getName().equals("Actor"))
                .collect(Collectors.toList());
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre findGenreByName(String name) {
        return genreRepository.findByName(name);
    }

    public List<Movie> getMoviesInGenre(String genreName) {
        Genre foundGenre = findGenreByName(genreName);
        return foundGenre.getMovies();
    }

    public List<Movie> getAll() {
        return movieRepository.findAll();
    }
}
