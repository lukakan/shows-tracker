package pl.lukakan.showstracker.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukakan.showstracker.user.model.Role;
import pl.lukakan.showstracker.user.model.User;
import pl.lukakan.showstracker.user.model.UserRole;

import java.util.Set;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    Set<UserRole> findByUser(User user);

    UserRole findByUserAndRole(User user, Role role);

}
