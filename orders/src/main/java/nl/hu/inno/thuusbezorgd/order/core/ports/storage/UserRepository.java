package nl.hu.inno.thuusbezorgd.order.core.ports.storage;


import nl.hu.inno.thuusbezorgd.order.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
