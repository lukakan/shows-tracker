package pl.lukakan.showstracker.show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pl.lukakan.showstracker.cast.role.model.Function;
import pl.lukakan.showstracker.cast.role.model.Role;
import pl.lukakan.showstracker.cast.role.repository.RoleRepository;
import pl.lukakan.showstracker.show.model.Genre;
import pl.lukakan.showstracker.show.model.Movie;
import pl.lukakan.showstracker.show.repository.GenreRepository;
import pl.lukakan.showstracker.show.repository.MovieRepository;
import pl.lukakan.showstracker.show.utils.FileUploadUtils;
import pl.lukakan.showstracker.show.utils.MoviePosterUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final RoleRepository roleRepository;
    private final MoviePosterUtils moviePosterUtils;

    @Autowired
    public MovieService(MovieRepository movieRepository, GenreRepository genreRepository, RoleRepository roleRepository, MoviePosterUtils moviePosterUtils) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.roleRepository = roleRepository;
        this.moviePosterUtils = moviePosterUtils;
    }

    public Optional<Movie> getById(Long id) {
        return movieRepository.findById(id);
    }

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public void saveMovieWithPicture(Movie movie, MultipartFile file) {
        Movie movieWithId;
        if (movie.getId() == null) {
            movieWithId = movieRepository.save(movie);
        } else {
            movieWithId = movie;
        }

        try {
            String posterFileName = FileUploadUtils.saveFileAndReturnFileName(moviePosterUtils.getMoviePosterSaveDir(), movieWithId.getId(), movieWithId.getTitle(), file);
            String posterImageFilePath = moviePosterUtils.createPosterImagePath(movieWithId.getId(), posterFileName);
            movieWithId.setPosterImageFilePath(posterImageFilePath);
            movieRepository.save(movieWithId);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void remove(Long movieId) {
        Movie movieToRemove = findById(movieId);
        movieToRemove.getRoles().forEach(roleRepository::delete);
        movieRepository.delete(movieToRemove);
    }

    public List<Role> getActors(Movie movie) {
        return movie.getRoles().stream()
                .filter(role -> role.getFunctionType().equals(Function.ACTOR))
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

    public Movie findById(Long movieId) {
        return movieRepository.findById(movieId).orElseThrow(IllegalArgumentException::new);
    }

    public void rateMovie(Long movieId, Double rate) {
        Movie movie = findById(movieId);
        Double avg = movie.getAverageRate();
        Integer noOfRates = movie.getNumberOfRates();
        if (noOfRates != null && avg != null) {
            avg = (avg * noOfRates + rate) / (noOfRates + 1);
            noOfRates++;
            avg = new BigDecimal(avg).setScale(2, RoundingMode.HALF_UP).doubleValue();
        } else {
            noOfRates = 1;
            avg = rate;
        }
        movie.setAverageRate(avg);
        movie.setNumberOfRates(noOfRates);
        save(movie);
    }
}
