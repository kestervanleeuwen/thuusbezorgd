package nl.hu.inno.thuusbezorgd.order.core.application;

import nl.hu.inno.thuusbezorgd.order.core.application.command.*;
import nl.hu.inno.thuusbezorgd.order.core.domain.*;
import nl.hu.inno.thuusbezorgd.order.core.domain.event.OrderEvent;
import nl.hu.inno.thuusbezorgd.order.core.domain.exception.UserNotFoundException;
import nl.hu.inno.thuusbezorgd.order.core.ports.messaging.OrderEventPublisher;
import nl.hu.inno.thuusbezorgd.order.core.ports.storage.DishRepository;
import nl.hu.inno.thuusbezorgd.order.core.ports.storage.OrderRepository;
import nl.hu.inno.thuusbezorgd.order.core.ports.storage.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderCommandHandler {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final DishRepository dishRepository;
    private final OrderEventPublisher eventPublisher;

    public OrderCommandHandler(OrderRepository orderRepository, UserRepository userRepository, DishRepository dishRepository, OrderEventPublisher eventPublisher) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.dishRepository = dishRepository;
        this.eventPublisher = eventPublisher;
    }

    public Order handle(NewOrder command) {
        User user = userRepository.findById(command.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Address address = new Address(command.getCity(), command.getStreet(), command.getHouseNr(), command.getZipcode());
        Order order = new Order(user, address);

        order.setStatus(OrderStatus.Received);

        for (Long dishId : command.getDishes()) {
            order.addDish(dishId);
        }

        publishEventsAndSave(order);
        Order savedOrder =  this.orderRepository.save(order);
        processOrder(savedOrder);
        return savedOrder;
    }

    public Order handle(CheckForOrderStatus command) {
        Order order = orderRepository.findById(command.getId())
                .orElseThrow(() -> new UserNotFoundException("Order not found"));

        order.readyForDelivery();

        publishEventsAndSave(order);
        return orderRepository.save(order);
    }

    public Order handle(ReceivedOrder command) {
        Order order = orderRepository.findById(command.getId())
                .orElseThrow(() -> new UserNotFoundException("Order not found"));

        order.setStatus(OrderStatus.Received);
        publishEventsAndSave(order);
        return this.orderRepository.save(order);
    }

    public Order handle(FinishOrder command) {
        Order order = orderRepository.findById(command.getId())
                .orElseThrow(() -> new UserNotFoundException("Order not found"));

        order.setStatus(OrderStatus.Delivered);
        publishEventsAndSave(order);
        return this.orderRepository.save(order);
    }



    public Order handle(AddOrderedDishToOrder command) {
        Order order = orderRepository.findById(command.getOrderId())
                .orElseThrow(() -> new UserNotFoundException("Order not found"));

        order.addDish(command.getDishId());
        publishEventsAndSave(order);
        return this.orderRepository.save(order);
    }

    public void processOrder(Order order) {
        dishRepository.processDishes(order);
    }

    private void publishEventsAndSave(Order order) {
        List<OrderEvent> events = order.listEvents();
        events.forEach(eventPublisher::publish);
        order.clearEvents();
    }
}
