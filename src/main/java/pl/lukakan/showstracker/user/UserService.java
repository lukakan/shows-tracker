package pl.lukakan.showstracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lukakan.showstracker.user.model.Role;
import pl.lukakan.showstracker.user.model.User;
import pl.lukakan.showstracker.user.model.UserDto;
import pl.lukakan.showstracker.user.model.UserRole;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(UserDto userDto) {
        String rawPassword = userDto.getPassword();
        String encryptedPassword = passwordEncoder.encode(rawPassword);
        userDto.setPassword(encryptedPassword);
        User user = fromDto(userDto);
        userRepository.save(user);
    }

    private User fromDto(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        Set<UserRole> defaultUserRole = Set.of(new UserRole(user, Role.ROLE_USER));
        user.setUserRole(new HashSet<>(defaultUserRole));
        return user;
    }
}
