package happy.service.user;

import happy.domain.user.Token;
import happy.repository.user.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class TokenCleaner {

    private static final Logger log = LoggerFactory.getLogger(TokenCleaner.class);

    private TokenRepository tokenRepository;

    @Autowired
    public TokenCleaner(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Scheduled(fixedRate = 60000)
    public void cleanUpExpiredTokens() {
        Iterable<Token> tokens = tokenRepository.findAll();
        List<Token> toDelete = new ArrayList<>();
        for (Token token : tokens) {
            if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
                toDelete.add(token);
            }
        }
        tokenRepository.deleteAll(toDelete);
        log.debug("Tokens cleaned.");
    }
}
