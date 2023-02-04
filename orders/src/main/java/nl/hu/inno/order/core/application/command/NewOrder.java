package nl.hu.inno.order.core.application.command;

import nl.hu.inno.order.core.domain.OrderStatus;
import nl.hu.inno.order.core.domain.OrderedDish;

import java.util.List;

// nee niet de band
public class NewOrder {
    private final Long userId;
    private final List<Long> dishes;
    private final String city;
    private final String street;
    private final String houseNr;
    private final String zipcode;

    public NewOrder(Long userId, List<Long> dishes, String city, String street, String houseNr, String zipcode) {
        this.userId = userId;
        this.dishes = dishes;
        this.city = city;
        this.street = street;
        this.houseNr = houseNr;
        this.zipcode = zipcode;
    }

    public Long getUserId() {
        return userId;
    }

    public List<Long> getDishes() {
        return dishes;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNr() {
        return houseNr;
    }

    public String getZipcode() {
        return zipcode;
    }
}
