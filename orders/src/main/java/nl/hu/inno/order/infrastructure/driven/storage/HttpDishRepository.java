package nl.hu.inno.order.infrastructure.driven.storage;

import com.google.gson.Gson;
import nl.hu.inno.order.core.domain.Order;
import nl.hu.inno.order.core.ports.storage.DishRepository;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<String,Object> requestBody = new HashMap<>();
        requestBody.put("dishIds", dishIds);

        Gson gson = new Gson();
        String json = gson.toJson(dishIds);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(json, headers);

        URI uri = URI.create(this.rootPath + "/dish/prepare");
        this.client.postForObject(uri, request, Void.class);
    }
}
