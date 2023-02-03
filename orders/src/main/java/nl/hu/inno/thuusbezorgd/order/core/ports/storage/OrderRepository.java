package nl.hu.inno.thuusbezorgd.order.core.ports.storage;

import nl.hu.inno.thuusbezorgd.order.core.domain.Order;
import nl.hu.inno.thuusbezorgd.order.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findByUser(User currentUser);
}
