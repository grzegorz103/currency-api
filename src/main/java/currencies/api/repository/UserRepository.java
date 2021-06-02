package currencies.api.repository;

import currencies.api.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPesel(String pesel);

    boolean existsByPesel(String value);
}
