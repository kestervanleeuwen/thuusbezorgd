package nl.hu.inno.order.infrastructure.driver.web;


import nl.hu.inno.order.core.application.OrderCommandHandler;
import nl.hu.inno.order.core.application.OrderQueryHandler;
import nl.hu.inno.order.core.application.command.NewUser;
import nl.hu.inno.order.core.application.query.GetUserById;
import nl.hu.inno.order.core.application.query.GetUsers;
import nl.hu.inno.order.core.domain.User;
import nl.hu.inno.order.infrastructure.driver.web.request.NewUserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final OrderCommandHandler commandHandler;
    private final OrderQueryHandler queryHandler;

    public UserController(OrderCommandHandler commandHandler, OrderQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @PostMapping
    public void newUser(@RequestBody NewUserRequest request) {
        this.commandHandler.handle(
                new NewUser(request.name, request.password)
        );
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable String id) {
       return this.queryHandler.handle(new GetUserById(id));
    }

    @GetMapping
    public List<User> getAllUsers(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return this.queryHandler.handle(new GetUsers(orderBy, direction));
    }
}
