package pl.lukakan.showstracker.show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.lukakan.showstracker.cast.role.model.Function;
import pl.lukakan.showstracker.cast.role.model.Role;
import pl.lukakan.showstracker.show.model.Genre;
import pl.lukakan.showstracker.show.model.Movie;

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


    @GetMapping("movies/genres")
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
        model.addAttribute("action", "add");
        model.addAttribute("movie", new Movie());
        model.addAttribute("allGenres", movieService.getAllGenres());
        model.addAttribute("editmode", false);
        return "movie/add";
    }

    @PostMapping("/movies/add")
    public String addMovie(Movie movieToAdd) {
        movieService.save(movieToAdd);
        Long id = movieToAdd.getId();
        return "redirect:/movies/" + id + "/edit";
    }

    @GetMapping("/movies/{id}/edit")
    public String movieEditPage(@PathVariable(name = "id") Long movieId, Model model) {
        Optional<Movie> movie = movieService.getById(movieId);

        if (movie.isPresent()) {
            List<Role> actors = movieService.getActors(movie.get());
            model.addAttribute("movieToDisplay", movie.get());
            model.addAttribute("roles", actors);
            return "movie/edit";
        } else {
            model.addAttribute("msg", "not found");
            return "error";
        }
    }

    @GetMapping("/movies/{id}/edit/editDetails")
    public String movieEditDetailsForm(@PathVariable(name = "id") Long movieId, Model model) {
        Optional<Movie> movie = movieService.getById(movieId);

        if (movie.isPresent()) {
            model.addAttribute("movie", movie.get());
            model.addAttribute("allGenres", movieService.getAllGenres());
            model.addAttribute("action", "update");
            model.addAttribute("editmode", true);
            return "movie/add";
        } else {
            model.addAttribute("msg", "not found");
            return "error";
        }
    }

    @PostMapping("/movies/{id}/edit/editDetails")
    public String movieEditDetails(@PathVariable(name = "id") Long movieId, Movie movie) {
        movieService.save(movie);
        return "redirect:/movies/" + movieId + "/edit";
    }

    @GetMapping("/movies/rate")
    public String rateMovie(@RequestParam(name = "rate") Double rate, @RequestParam(name = "id") Long movieId) {
        movieService.rateMovie(movieId, rate);
        return "redirect:/movies/movie?id=" + movieId;
    }

    @GetMapping("/movies/{id}/remove")
    public String removeMovie(@PathVariable(name = "id") Long id) {
        movieService.remove(id);
        return "redirect:/movies";
    }
}
