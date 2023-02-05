package nl.hu.inno.order.core.ports.storage;


import nl.hu.inno.order.core.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
