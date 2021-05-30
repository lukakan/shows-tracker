package pl.lukakan.showstracker.cast.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.lukakan.showstracker.cast.person.model.Person;
import pl.lukakan.showstracker.cast.role.model.Function;
import pl.lukakan.showstracker.cast.role.model.Role;

import java.util.List;
import java.util.Set;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    @Query("SELECT r.person FROM Role r WHERE r.functionType = :function")
    Set<Person> findAllByFunctionType(@Param("function") Function function);

}
