package nl.hu.inno.delivery.infrastructure.driver.web;


import nl.hu.inno.delivery.core.application.DeliveryCommandHandler;
import nl.hu.inno.delivery.core.application.DeliveryQueryHandler;
import nl.hu.inno.delivery.core.application.query.GetDeliveries;
import nl.hu.inno.delivery.core.application.query.GetDeliveryById;
import nl.hu.inno.delivery.core.domain.Delivery;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController {
    private final DeliveryCommandHandler commandHandler;
    private final DeliveryQueryHandler queryHandler;

    public DeliveryController(DeliveryCommandHandler commandHandler, DeliveryQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @GetMapping
    public List<Delivery> getAllDeliveries(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return this.queryHandler.handle(new GetDeliveries(orderBy, direction));
    }

    @GetMapping("/{id}")
    public Delivery getDeliveryById(@PathVariable Long id) {
        return this.queryHandler.handle(new GetDeliveryById(id));
    }




}
