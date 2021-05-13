package pl.lukakan.showstracker.show.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import pl.lukakan.showstracker.cast.role.model.Function;
import pl.lukakan.showstracker.cast.role.model.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate premiereDate;
    private String posterImageName;
    private String description;
    private Double averageRate;
    private Integer numberOfRates;
    @OneToMany(mappedBy = "movie")
    private List<Role> roles;
    @ManyToMany
    @JoinTable(
            name = "movies_genres",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres;

    @Transient
    private String moviePosterSaveDir = "poster/";

    @Transient
    private String posterImagePath;

    public Movie() {

    }

    /*TODO: figure out how to replace this function by service*/
    public String getDirectorName() {
        return roles.stream()
                .filter(role -> role.getFunction().equals(Function.DIRECTOR))
                .map(role -> role.getPerson().getFirstName() + " " + role.getPerson().getLastName() + " ")
                .reduce(String::concat).orElse("");
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getPremiereDate() {
        return premiereDate;
    }

    public void setPremiereDate(LocalDate premiereDate) {
        this.premiereDate = premiereDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAverageRate() {
        return averageRate;
    }

    public void setAverageRate(Double averageRate) {
        this.averageRate = averageRate;
    }

    public Integer getNumberOfRates() {
        return numberOfRates;
    }

    public void setNumberOfRates(Integer numberOfRates) {
        this.numberOfRates = numberOfRates;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }


    public Set<Genre> getGenres() {
        return genres;
    }

    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }

    public String getPosterImageName() {
        return posterImageName;
    }

    public void setPosterImageName(String posterImageSrc) {
        this.posterImageName = posterImageSrc;
    }


    @Transient
    public String getPosterImagePath() {
        if (posterImageName == null || id == null) return null;
        return moviePosterSaveDir + id + "/" + posterImageName;
    }

    @Transient
    public String getMoviePosterSaveDir() {
        return moviePosterSaveDir;
    }
}
