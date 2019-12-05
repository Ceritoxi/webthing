package happy.repository.user;

import happy.domain.user.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Long> {
    Iterable<Token> findAllByToken(String token);
}
