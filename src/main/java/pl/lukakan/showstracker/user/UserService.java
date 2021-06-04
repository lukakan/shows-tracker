package pl.lukakan.showstracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        Set<UserRole> defaultUserRole = Set.of(new UserRole(user, Role.ROLE_USER));
        user.setUserRole(new HashSet<>(defaultUserRole));
        return user;
    }

    private UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());

        return userDto;
    }

    public UserDto findUserByName(String userName) {
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        if (optionalUser.isPresent()) {
            return toDto(optionalUser.get());
        } else {
            throw new UsernameNotFoundException("User name " + userName + " not found");
        }
    }

    public boolean isLoginUnique(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);
        return user.isEmpty();
    }


    public void updateUserDetails(String userName, UserDto editedUser) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setFirstName(editedUser.getFirstName());
            user.setLastName(editedUser.getLastName());
            user.setEmail(editedUser.getEmail());
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("User name " + userName + " not found");
        }

    }

    public boolean isOldPasswordValid(String userName, String oldPassword) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return passwordEncoder.matches(oldPassword, user.getPassword());
        }
        return false;
    }

    public void updateUserPassword(String userName, String newPassword) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String encodedPassword = passwordEncoder.encode(newPassword);
            user.setPassword(encodedPassword);
            userRepository.save(user);
        } else {
            throw new UsernameNotFoundException("User name " + userName + " not found");
        }
    }
}
