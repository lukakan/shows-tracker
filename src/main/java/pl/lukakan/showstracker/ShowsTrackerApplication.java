package pl.lukakan.showstracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import pl.lukakan.showstracker.show.utils.MoviePosterUtils;

@SpringBootApplication
public class ShowsTrackerApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ShowsTrackerApplication.class, args);
    }

}
