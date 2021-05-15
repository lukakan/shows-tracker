package pl.lukakan.showstracker.cast.person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lukakan.showstracker.cast.person.model.Person;
import pl.lukakan.showstracker.cast.person.repository.PersonRepository;
import pl.lukakan.showstracker.cast.role.model.Function;
import pl.lukakan.showstracker.cast.role.model.Role;
import pl.lukakan.showstracker.cast.role.repository.RoleRepository;


import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PersonService {

    private final PersonRepository personRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleRepository roleRepository) {
        this.personRepository = personRepository;
        this.roleRepository = roleRepository;
    }

    public List<Person> findAllPersons() {
        return personRepository.findAll();
    }

    public List<Function> getFunctions() {
        return Arrays.asList(Function.values());
    }

    public Set<Person> findAllPersonInFunction(String functionName) {
        Function function = Function.getFunctionByName(functionName);
        List<Role> roles = roleRepository.findAllByFunctionType(function);
        return roles.stream().map(role -> role.getPerson()).collect(Collectors.toSet());
    }

    public void save(Person person) {
        personRepository.save(person);
    }


}
