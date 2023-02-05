package nl.hu.inno.order.infrastructure.driver.web;

import nl.hu.inno.order.core.application.OrderCommandHandler;
import nl.hu.inno.order.core.application.OrderQueryHandler;
import nl.hu.inno.order.core.application.command.CheckForOrderStatus;
import nl.hu.inno.order.core.application.command.NewOrder;
import nl.hu.inno.order.core.application.query.GetOrderById;
import nl.hu.inno.order.core.application.query.GetOrderByUser;
import nl.hu.inno.order.core.application.query.GetOrders;
import nl.hu.inno.order.core.application.query.GetUserById;
import nl.hu.inno.order.core.domain.*;
import nl.hu.inno.order.core.ports.storage.ReviewRepository;
import nl.hu.inno.order.infrastructure.driver.web.request.DeliveryReviewRequest;
import nl.hu.inno.order.infrastructure.driver.web.request.DishReviewRequest;
import nl.hu.inno.order.infrastructure.driver.web.request.NewOrderRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderCommandHandler commandHandler;
    private final OrderQueryHandler queryHandler;
    private final ReviewRepository reviewRepository;

    public OrderController(OrderCommandHandler commandHandler, OrderQueryHandler queryHandler, ReviewRepository reviewRepository) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
        this.reviewRepository = reviewRepository;
    }

    @GetMapping
    public List<Order> getAllOrders(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return this.queryHandler.handle(new GetOrders(orderBy, direction));
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return this.queryHandler.handle(new GetOrderById(id));
    }

    @GetMapping("/user/{user}")
    public List<Order> getOrdersByUser(@PathVariable String user) {
        return this.queryHandler.handle(new GetOrderByUser(user));
    }

    @PostMapping
    public Order newOrder(@RequestBody NewOrderRequest newOrder) {
        return this.commandHandler.handle(
                new NewOrder(newOrder.userId, newOrder.dishes, newOrder.city, newOrder.street, newOrder.houseNr, newOrder.zipcode)
        );
    }

    @PostMapping("/check/{id}")
    public Order checkOrderStatus(@PathVariable Long id) {
        return this.commandHandler.handle(
                new CheckForOrderStatus(id)
        );
    }

    @PostMapping("/dishreview")
    public Review dishReview(@RequestBody DishReviewRequest request) {
        User user = queryHandler.handle(new GetUserById(request.userId));
        ReviewRating rating = ReviewRating.fromInt(request.rating);
        DishReview dishReview = new DishReview(request.dishId, rating, user);
        return this.reviewRepository.save(dishReview);
    }

    @PostMapping("/deliveryreview")
    public Review delivery(@RequestBody DeliveryReviewRequest request) {
        User user = queryHandler.handle(new GetUserById(request.userId));
        ReviewRating rating = ReviewRating.fromInt(request.rating);
        DishReview dishReview = new DishReview(request.deliveryId, rating, user);
        return this.reviewRepository.save(dishReview);
    }

}
