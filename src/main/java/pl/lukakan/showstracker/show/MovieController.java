package pl.lukakan.showstracker.show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.lukakan.showstracker.cast.Role;
import pl.lukakan.showstracker.show.model.Genre;
import pl.lukakan.showstracker.show.model.Movie;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public String moviesPage(Model model) {
        List<Movie> movies = movieService.getAll();
        List<Genre> genres = movieService.getAllGenres();
        model.addAttribute("movies", movies);
        model.addAttribute("genres", genres);
        return "movie/list";
    }


    @GetMapping("/genres")
    public String moviesInCategoryPage(@RequestParam(name = "category") String genreName, Model model) {
        List<Movie> movies = movieService.getMoviesInGenre(genreName);
        List<Genre> genres = movieService.getAllGenres();
        model.addAttribute("movies", movies);
        model.addAttribute("genres", genres);
        return "movie/list";
    }


    @GetMapping("/movies/movie")
    public String moviePage(@RequestParam(name = "id") Long movieId, Model model) {
        Optional<Movie> movie = movieService.getById(movieId);

        if (movie.isPresent()) {
            List<Role> actors = movieService.getActors(movie.get());
            model.addAttribute("movieToDisplay", movie.get());
            model.addAttribute("roles", actors);
            return "movie/single";
        } else {
            model.addAttribute("msg", "not found");
            return "error";
        }
    }

    @GetMapping("/movies/add")
    public String addMovieForm(Model model) {
        List<Genre> selectionList = new ArrayList<>();
        model.addAttribute("action", "add");
        model.addAttribute("movie", new Movie());
        model.addAttribute("allGenres", movieService.getAllGenres());
        return "movie/add";
    }

    @PostMapping("/movies/add")
    public String addMovie(Movie movieToAdd) {
        movieService.add(movieToAdd);
        return "redirect:/movies";
    }
}
