package pl.lukakan.showstracker.cast;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String country;
    @Enumerated(EnumType.STRING)
    private Gender gender;

}
