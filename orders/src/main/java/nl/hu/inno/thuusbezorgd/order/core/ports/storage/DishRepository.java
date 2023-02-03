package nl.hu.inno.thuusbezorgd.order.core.ports.storage;

import nl.hu.inno.thuusbezorgd.order.core.domain.Order;

public interface DishRepository {
    void processDishes(Order order);
}
