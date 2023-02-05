package nl.hu.inno.order.infrastructure.driver.web.request;

import javax.validation.constraints.NotNull;

public class NewUserRequest {
    @NotNull
    public String name;

    @NotNull
    public String password;
}
