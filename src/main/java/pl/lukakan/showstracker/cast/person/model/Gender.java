package pl.lukakan.showstracker.cast.person.model;

public enum Gender {
    MALE("mężczyzna"), FEMALE("kobieta");
    private final String description;

    Gender(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
