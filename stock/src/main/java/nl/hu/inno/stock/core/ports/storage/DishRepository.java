package nl.hu.inno.stock.core.ports.storage;

import nl.hu.inno.stock.core.domain.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
    Dish findByName(String name);
}