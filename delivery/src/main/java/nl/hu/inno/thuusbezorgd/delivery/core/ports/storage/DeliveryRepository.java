package nl.hu.inno.thuusbezorgd.delivery.core.ports.storage;

import nl.hu.inno.thuusbezorgd.delivery.core.domain.Delivery;
import nl.hu.inno.thuusbezorgd.delivery.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    @Query(name = "Delivery.findRandom")
    Optional<Delivery> findRandomDelivery();

    List<Delivery> findByOrder_User(User user);
}
