package nl.hu.inno.delivery.core.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Rider {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    public Rider(String name) {
        this.name = name;
    }

    protected Rider() {

    }

    @OneToMany(mappedBy = "rider", fetch = FetchType.LAZY)
    private List<Delivery> deliveries = new ArrayList<>();

    public void addDelivery(Delivery delivery) {
        deliveries.add(delivery);
    }



    public String getName() {
        return this.name;
    }
}
