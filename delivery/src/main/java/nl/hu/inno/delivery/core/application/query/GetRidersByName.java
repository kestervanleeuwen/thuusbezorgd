package nl.hu.inno.delivery.core.application.query;

public class GetRidersByName {
    private final String name;

    public GetRidersByName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
