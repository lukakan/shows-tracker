package pl.lukakan.showstracker.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lukakan.showstracker.user.model.*;

import java.util.*;
import java.util.stream.Collectors;

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
        User user = fromDto(userDto);
        saveUserWithProvidedPassword(rawPassword, user);
    }

    private User fromDto(UserDto userDto) {
        User user = new User();
        user.setUserName(userDto.getUserName());
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUserRoles(Set.of(new UserRole(user, Role.ROLE_USER)));
        return user;
    }

    private UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserName(user.getUserName());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setRoles(user.getUserRoles().stream()
                .map(UserRole::getRole)
                .collect(Collectors.toSet()));

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

    private boolean isOldPasswordValid(String oldPasswordProvidedByUser, String oldPasswordFromDb) {
        return passwordEncoder.matches(oldPasswordProvidedByUser, oldPasswordFromDb);
    }

    public void updateUserPassword(String userName, String newPassword, String oldPasswordProvidedByUser) {
        Optional<User> userOptional = userRepository.findByUserName(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String oldPasswordFromDb = user.getPassword();
            if (isOldPasswordValid(oldPasswordProvidedByUser, oldPasswordFromDb)) {
                saveUserWithProvidedPassword(newPassword, user);
            } else {
                throw new IllegalArgumentException("Provided old password for verification is not correct");
            }
        } else {
            throw new UsernameNotFoundException("User name " + userName + " not found");
        }
    }

    private void saveUserWithProvidedPassword(String password, User user) {
        String encodedPassword = passwordEncoder.encode(password);
        user.setPassword(encodedPassword);
        userRepository.save(user);
    }

    public Set<UserDto> findAllExceptCurrent(String currentUserName) {
        Set<User> users = userRepository.findByUserNameNot(currentUserName);
        return users.stream().map(this::toDto).collect(Collectors.toSet());
    }


    public void promoteUserToAdmin(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserRole userRole = new UserRole(user, Role.ROLE_ADMIN);
            user.addUserRole(userRole);
            userRepository.save(user);
        }
    }

    public void degradeAdmin(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.removeUserRole(Role.ROLE_ADMIN);
            userRepository.save(user);
        }
    }
}
