package happy.service.user.login;

import happy.util.SecurityUtil;
import happy.domain.user.Token;
import happy.domain.user.User;
import happy.model.user.login.LoginRequest;
import happy.model.user.login.exception.LoginAttemptFailedException;
import happy.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class LoginService {

    private TokenService tokenService;
    private UserRepository userRepository;

    @Autowired
    public LoginService(TokenService tokenService, UserRepository userRepository) {
        this.tokenService = tokenService;
        this.userRepository = userRepository;
    }

    public Token login(@Valid LoginRequest loginRequest) {
        Iterable<User> allByEmail = userRepository.findAllByEmail(loginRequest.getEmail());
        for (User user : allByEmail) {
            if (SecurityUtil.isSame(loginRequest.getPassword(), user.getHash(), user.getSalt())) {
                return tokenService.createToken(user);
            }
        }
        throw new LoginAttemptFailedException("No user exists with such credentials!");
    }
}
