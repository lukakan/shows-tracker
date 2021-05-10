package pl.lukakan.showstracker.cast.role.model;

public enum Function {
    ACTOR("Aktor"), DIRECTOR("Reżyser"), WRITER("Scenarzysta");

    private final String name;

    Function(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
