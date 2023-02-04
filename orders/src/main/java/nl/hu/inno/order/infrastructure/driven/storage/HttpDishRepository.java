package nl.hu.inno.order.infrastructure.driven.storage;

import nl.hu.inno.order.core.domain.Order;
import nl.hu.inno.order.core.ports.storage.DishRepository;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class HttpDishRepository implements DishRepository {
    private final String rootPath;
    private final RestTemplate client;

    public HttpDishRepository(String rootPath, RestTemplate client) {
        this.rootPath = rootPath;
        this.client = client;
    }

    @Override
    public void processDishes(Order order) {
        List<Long> dishIds = new ArrayList<>();

        order.getOrderedDishIds().forEach(dishId-> {
            dishIds.add(dishId);
        });

        URI uri = URI.create(this.rootPath + "/stock/prepare");
        this.client.postForObject(uri, dishIds, Void.class);
    }
}
