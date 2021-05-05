package pl.lukakan.showstracker.cast;

import pl.lukakan.showstracker.show.Movie;

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
    @ManyToOne
    private Function function;
}
