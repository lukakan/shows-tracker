package pl.lukakan.showstracker.cast.role.model;

import java.util.Arrays;

public enum Function {
    ACTOR("Aktor"), DIRECTOR("Reżyser"), WRITER("Scenarzysta");

    private final String name;

    Function(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Function getFunctionByName(String name) {
        for (Function value : Function.values()) {
            if (value.getName().equals(name)) {
                return value;
            }
        }
        return null;
    }
}
