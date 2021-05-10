package pl.lukakan.showstracker.cast.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukakan.showstracker.cast.person.model.Person;
import pl.lukakan.showstracker.cast.person.repository.PersonRepository;
import pl.lukakan.showstracker.cast.role.model.Function;


import java.util.Arrays;
import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public List<Function> getFunctions() {
        return Arrays.asList(Function.values());
    }

    public void save(Person person) {
        personRepository.save(person);
    }


}
