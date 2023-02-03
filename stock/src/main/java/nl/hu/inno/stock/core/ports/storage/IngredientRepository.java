package nl.hu.inno.stock.core.ports.storage;

import nl.hu.inno.stock.core.domain.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    Ingredient findByName(String name);
}
