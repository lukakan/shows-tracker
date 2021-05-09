package pl.lukakan.showstracker.cast.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.lukakan.showstracker.cast.role.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
