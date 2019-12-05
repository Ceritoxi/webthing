package happy.service.user.login;

import happy.util.SecurityUtil;
import happy.domain.user.Token;
import happy.domain.user.User;
import happy.repository.user.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@Transactional
public class TokenService {

    private TokenRepository tokenRepository;

    @Autowired
    public TokenService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public Token createToken(User user) {
        Token token = new Token();
        token.setToken(SecurityUtil.generateToken());
        token.setExpiryDate(LocalDateTime.now().plus(1, ChronoUnit.HOURS));
        token.setUser(user);
        tokenRepository.save(token);
        return token;
    }

    public Optional<User> getByToken(String value) {
        Optional<User> result = Optional.empty();
        Iterable<Token> allByToken = tokenRepository.findAllByToken(value);
        for (Token token : allByToken) {
            if (token.getExpiryDate().isAfter(LocalDateTime.now())) {
                result = Optional.of(token.getUser());
            }
        }
        return result;
    }
}
