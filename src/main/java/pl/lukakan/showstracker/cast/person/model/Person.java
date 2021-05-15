package pl.lukakan.showstracker.cast.person.model;

import org.springframework.format.annotation.DateTimeFormat;
import pl.lukakan.showstracker.cast.role.model.Function;
import pl.lukakan.showstracker.cast.role.model.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    private String country;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "person")
    private List<Role> roles;


    public List<Role> getRoles() {
        return roles;
    }

    public Set<Function> functions() {
        return roles.stream().map(Role::getFunctionType).collect(Collectors.toSet());
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
