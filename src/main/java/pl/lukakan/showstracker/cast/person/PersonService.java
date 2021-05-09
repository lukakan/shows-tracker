package pl.lukakan.showstracker.cast.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukakan.showstracker.cast.person.model.Person;
import pl.lukakan.showstracker.cast.person.repository.PersonRepository;
import pl.lukakan.showstracker.cast.role.model.Function;
import pl.lukakan.showstracker.cast.role.repository.FunctionRepository;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final FunctionRepository functionRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, FunctionRepository functionRepository) {
        this.personRepository = personRepository;
        this.functionRepository = functionRepository;
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public List<Function> findAllFunctions() {
        return functionRepository.findAll();
    }

    public void save(Person person) {
        personRepository.save(person);
    }


}
