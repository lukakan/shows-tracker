package pl.lukakan.showstracker.cast.person.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lukakan.showstracker.cast.person.model.Person;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

}
