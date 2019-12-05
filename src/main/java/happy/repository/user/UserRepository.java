package happy.repository.user;

import happy.domain.user.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    boolean existsByEmail(String name);
    Iterable<User> findAllByEmail(String email);
}
