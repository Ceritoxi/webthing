package happy.model.user.login.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class LoginAttemptFailedException extends RuntimeException {

    public LoginAttemptFailedException(String message) {
        super(message);
    }
}
