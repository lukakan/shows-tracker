package pl.lukakan.showstracker;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import pl.lukakan.showstracker.show.utils.MoviePosterUtils;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final MoviePosterUtils moviePosterUtils;

    @Autowired
    public MvcConfig(MoviePosterUtils moviePosterUtils) {
        this.moviePosterUtils = moviePosterUtils;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        exposeDirectory(moviePosterUtils.getMoviePosterSaveDir(), registry);
    }

    private void exposeDirectory(String dirName, ResourceHandlerRegistry registry) {
        Path uploadDir = Paths.get(dirName);
        String uploadPath = uploadDir.toFile().getAbsolutePath();

        if (dirName.startsWith("../")) dirName = dirName.replace("../", "");

        registry.addResourceHandler("/" + dirName + "/**").addResourceLocations("file:/" + uploadPath + "/");
    }
}
