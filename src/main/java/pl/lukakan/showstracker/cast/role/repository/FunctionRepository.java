package pl.lukakan.showstracker.cast.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lukakan.showstracker.cast.role.model.Function;

@Repository
public interface FunctionRepository extends JpaRepository<Function, Long> {

}
