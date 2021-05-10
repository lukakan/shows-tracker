package pl.lukakan.showstracker.cast.person.model;

public enum Gender {
    MALE("mężczyzna"), FEMALE("kobieta");
    private final String name;

    Gender(String description) {
        this.name = description;
    }

    public String getName() {
        return name;
    }
}
