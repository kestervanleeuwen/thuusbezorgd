package nl.hu.inno.delivery.core.application.command;

public class NewRider {
    private final String name;

    public NewRider(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
