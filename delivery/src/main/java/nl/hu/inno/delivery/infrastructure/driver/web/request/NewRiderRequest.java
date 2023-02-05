package nl.hu.inno.delivery.infrastructure.driver.web.request;

import javax.validation.constraints.NotNull;

public class NewRiderRequest {
    @NotNull
    public String name;
}
