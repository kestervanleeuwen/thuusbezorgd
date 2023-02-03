package nl.hu.inno.thuusbezorgd.order.infrastructure.driver.web.request;


import javax.validation.constraints.NotBlank;
import java.util.List;

public class NewOrderRequest {
    @NotBlank
    public Long userId;
    @NotBlank
    public List<Long> dishes;
    @NotBlank
    public String city;
    @NotBlank
    public String street;
    @NotBlank
    public String houseNr;
    @NotBlank
    public String zipcode;
}
