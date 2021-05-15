package pl.lukakan.showstracker.cast.role.model;

import pl.lukakan.showstracker.cast.person.model.Person;
import pl.lukakan.showstracker.show.model.Movie;

import javax.persistence.*;

@Entity
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    String name;
    @ManyToOne
    private Person person;
    @ManyToOne
    private Movie movie;
    @Enumerated(EnumType.STRING)
    private Function functionType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Function getFunctionType() {
        return functionType;
    }

    public void setFunctionType(Function function) {
        this.functionType = function;
    }
}
