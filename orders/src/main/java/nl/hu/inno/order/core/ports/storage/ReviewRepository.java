package nl.hu.inno.order.core.ports.storage;

import nl.hu.inno.order.core.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
