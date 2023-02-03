package nl.hu.inno.thuusbezorgd.order.core.application;

import nl.hu.inno.thuusbezorgd.order.core.application.query.GetOrderById;
import nl.hu.inno.thuusbezorgd.order.core.application.query.GetOrderByUser;
import nl.hu.inno.thuusbezorgd.order.core.application.query.GetOrders;
import nl.hu.inno.thuusbezorgd.order.core.domain.Order;
import nl.hu.inno.thuusbezorgd.order.core.domain.User;
import nl.hu.inno.thuusbezorgd.order.core.domain.exception.OrderNotFoundException;
import nl.hu.inno.thuusbezorgd.order.core.ports.storage.OrderRepository;
import nl.hu.inno.thuusbezorgd.order.core.ports.storage.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderQueryHandler {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public OrderQueryHandler(OrderRepository orderRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public Order handle(GetOrderById query) {
        return orderRepository.findById(query.getId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));
    }

    public List<Order> handle(GetOrderByUser query) {
        User user = userRepository.findById(query.getUserId())
                .orElseThrow(() -> new OrderNotFoundException("Order not found"));

        return orderRepository.findByUser(user);
    }

    public List<Order> handle(GetOrders query) {
        Sort sort = createSort(query.getOrderBy(), query.getDirection());
        return orderRepository.findAll(sort);
    }

    private Sort createSort(String orderBy, String direction) {
        Sort sort = Sort.by(Sort.Direction.ASC, orderBy);

        if (direction.equals("desc")) {
            sort = sort.descending();
        }

        return sort;
    }
}
