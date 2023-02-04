package nl.hu.inno.delivery.infrastructure.driver.web;

import nl.hu.inno.delivery.core.application.DeliveryCommandHandler;
import nl.hu.inno.delivery.core.application.DeliveryQueryHandler;
import nl.hu.inno.delivery.core.application.query.GetRiderById;
import nl.hu.inno.delivery.core.application.query.GetRiders;
import nl.hu.inno.delivery.core.application.query.GetRidersByName;
import nl.hu.inno.delivery.core.domain.Rider;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rider")
public class RiderController {
    private final DeliveryCommandHandler commandHandler;
    private final DeliveryQueryHandler queryHandler;

    public RiderController(DeliveryCommandHandler commandHandler, DeliveryQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @GetMapping()
    public List<Rider> getAllRiders(
            @RequestParam(required = false) String orderBy,
            @RequestParam(required = false) String direction
    ) {
        return this.queryHandler.handle(new GetRiders(orderBy, direction));
    }

    @GetMapping("/{id}")
    public Rider getRiderById(@PathVariable Long id) {
        return this.queryHandler.handle(new GetRiderById(id));
    }

    @GetMapping("/{name}")
    public List<Rider> getRiderByName(@PathVariable String name) {
        return this.queryHandler.handle(new GetRidersByName(name));
    }

}
