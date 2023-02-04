package nl.hu.inno.order.core.ports.storage;

import nl.hu.inno.order.core.domain.Order;

public interface DishRepository {
    void processDishes(Order order);
}
