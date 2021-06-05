package pl.lukakan.showstracker.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.lukakan.showstracker.user.model.Role;
import pl.lukakan.showstracker.user.model.User;
import pl.lukakan.showstracker.user.model.UserRole;


public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    UserRole findByUserAndRole(User user, Role role);

}
