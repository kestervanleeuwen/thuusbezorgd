package nl.hu.inno.delivery.core.domain;


import nl.hu.inno.delivery.core.domain.event.DeliveryEvent;
import nl.hu.inno.delivery.core.domain.event.OrderCompleted;
import nl.hu.inno.delivery.core.domain.event.OrderReceived;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "delivery")
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    @Column
    private boolean completed;

    @ManyToOne
    private Rider rider;

    @Column(name = "order_id")
    private Long order;

    @Transient
    private List<DeliveryEvent> events = new ArrayList<>();

    protected Delivery(){}

    public Delivery(Rider rider){
        this.rider = rider;
        this.completed = false;
    }

    public void setStatus(boolean status) {
        this.completed = status;
    }


    public void setOrder(Long order) {
        this.order = order;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void markCompleted(){
        this.completed = true;
        events.add(new OrderCompleted(this.order));
    }

    public List<DeliveryEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        events.clear();
    }

    public void orderReceived(){
        events.add(new OrderReceived(this.order));
    }
}
