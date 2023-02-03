package nl.hu.inno.thuusbezorgd.order.core.domain;



import nl.hu.inno.thuusbezorgd.order.core.application.command.NewOrder;
import nl.hu.inno.thuusbezorgd.order.core.domain.event.NewOrderEvent;
import nl.hu.inno.thuusbezorgd.order.core.domain.event.OrderEvent;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "orders") //Order is een keyword in sql, so this works around some wonky sql-generator implementations
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User user;

    private LocalDateTime orderDate;

    public LocalDateTime getOrderDate() {
        return orderDate;
    }


    private Long delivery;

    private Address address;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public OrderStatus getStatus() {
        return status;
    }

    @OneToMany(mappedBy = "id.order")
    @Cascade(CascadeType.PERSIST)
    private List<OrderedDish> orderedDishes;
    @Transient
    private List<OrderEvent> events = new ArrayList<>();

    protected Order() {

    }

    public Order(User u, Address address) {
        this.user = u;
        this.orderedDishes = new ArrayList<>();
        this.address = address;
        this.status = OrderStatus.Received;
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

//    public List<Long> getOrderedDishes() {
//        return this.orderedDishes.stream().map(OrderedDish::getDish).toList();
//    }

    public List<Long> getOrderedDishIds() {
        List<Long> ordered = new ArrayList<>();

        for (OrderedDish od : this.orderedDishes) {
            ordered.add(od.getDish());
        }

        return Collections.unmodifiableList(ordered);
    }

    public void addDish(Long dish) {
        this.orderedDishes.add(new OrderedDish(this, dish));
    }

    public Address getAddress() {
        return address;
    }

    public Long getDelivery() {
        return delivery;
    }

    public void setDelivery(Long delivery) {
        this.delivery = delivery;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void readyForDelivery() {
        if (this.status == OrderStatus.ReadyForDelivery) {
            this.events.add(new NewOrderEvent(id));
        } else {
            // 80% chance your order goes to the next status when you check it... poor customers
            Random rand = new Random();
            boolean val = rand.nextInt(100) < 80;

            this.status.next();
        }
    }

    public List<OrderEvent> listEvents() {
        return events;
    }

    public void clearEvents() {
        events.clear();
    }
}
