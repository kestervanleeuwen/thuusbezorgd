package nl.hu.inno.order.core.application.command;

public class NewUser {
    private final String name;
    private final String password;

    public NewUser(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
