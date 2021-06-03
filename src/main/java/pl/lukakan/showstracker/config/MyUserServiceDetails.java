package pl.lukakan.showstracker.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.lukakan.showstracker.user.UserRepository;
import pl.lukakan.showstracker.user.model.User;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MyUserServiceDetails implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserServiceDetails(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByUserName(userName);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            Collection<SimpleGrantedAuthority> roles = user.getUserRole().stream()
                    .map(userRole -> new SimpleGrantedAuthority(userRole.getRole().name()))
                    .collect(Collectors.toSet());
            return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), roles);
        }

        throw new UsernameNotFoundException("User name " + userName + " not found");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
