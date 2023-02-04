package nl.hu.inno.delivery.core.ports.storage;

import nl.hu.inno.delivery.core.domain.Rider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RiderRepository extends JpaRepository<Rider, Long> {
    List<Rider> findAllByName(String name);

    @Query("SELECT r FROM Rider r JOIN r.deliveries del ORDER BY COUNT(del) ASC")
    Rider findRiderWithLeastDeliveries();
}
