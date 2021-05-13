package pl.lukakan.showstracker.cast.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukakan.showstracker.cast.person.model.Person;
import pl.lukakan.showstracker.cast.person.repository.PersonRepository;
import pl.lukakan.showstracker.cast.role.model.Function;
import pl.lukakan.showstracker.cast.role.model.Role;
import pl.lukakan.showstracker.cast.role.repository.RoleRepository;
import pl.lukakan.showstracker.show.model.Movie;
import pl.lukakan.showstracker.show.repository.MovieRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final PersonRepository personRepository;
    private final MovieRepository movieRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository, PersonRepository personRepository, MovieRepository movieRepository) {
        this.roleRepository = roleRepository;
        this.personRepository = personRepository;
        this.movieRepository = movieRepository;
    }

    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    public List<Function> getFunctions() {
        return Arrays.asList(Function.values());
    }

    public void save(Role role, Long movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        role.setMovie(movie);
        roleRepository.save(role);
    }

}
