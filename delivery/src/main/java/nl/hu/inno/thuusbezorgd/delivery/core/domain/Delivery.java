package nl.hu.inno.thuusbezorgd.delivery.core.domain;


import nl.hu.inno.thuusbezorgd.delivery.core.application.command.NewDelivery;
import nl.hu.inno.thuusbezorgd.delivery.core.domain.event.DeliveryEvent;
import nl.hu.inno.thuusbezorgd.delivery.core.domain.event.OrderCompleted;
import nl.hu.inno.thuusbezorgd.delivery.core.domain.event.OrderReceived;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedNativeQuery(name = "Delivery.findRandom", resultClass = Delivery.class,
        query = "select * from delivery order by random() limit 1")
public class Delivery {
    @Id
    @GeneratedValue
    private Long id;

    private boolean completed;

    @ManyToOne
    private Rider rider;


    private Long order;
    @Transient
    private List<DeliveryEvent> events = new ArrayList<>();

    protected Delivery(){}

    public Delivery(Rider rider){
        this.rider = rider;
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
