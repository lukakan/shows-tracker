package pl.lukakan.showstracker.show.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pl.lukakan.showstracker.show.model.Movie;

@Component
public class MoviePosterUtils {


    private final String moviePosterSaveDir;

    public MoviePosterUtils(@Value("${movie.poster.dir}") String moviePosterSaveDir) {
        this.moviePosterSaveDir = moviePosterSaveDir;
    }

    public String createPosterImagePath(Long id, String fileName) {
        if (fileName == null || id == null) {
            return null;
        }
        return moviePosterSaveDir + "/" + id + "/" + fileName;
    }

    public String getMoviePosterSaveDir() {
        return moviePosterSaveDir + "/";
    }

}
