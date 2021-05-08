package pl.lukakan.showstracker.show.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.format.annotation.DateTimeFormat;
import pl.lukakan.showstracker.cast.Person;
import pl.lukakan.showstracker.cast.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate premiereDate;
    private String description;
    private Double averageRate;
    private Integer numberOfRates;
    @OneToMany(mappedBy = "movie")
    private List<Role> roles;
    @ManyToMany(mappedBy = "movies")
    private List<Genre> genres;

    public Movie() {

    }

//    public void addGenres(Genre genre) {
//        genre.addMovie(this);
//        genres.add(genre);
//    }

    public String getGenresNames() {
        return genres.stream()
                .map(genre -> genre.getName() + " ")
                .reduce(String::concat).orElse("no info");
    }

    public String getDirectorName() {
        return roles.stream()
                .filter(role -> role.getFunction().getName().equals("Director"))
                .map(role -> role.getPerson().getFirstName() + " " + role.getPerson().getLastName())
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


    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
