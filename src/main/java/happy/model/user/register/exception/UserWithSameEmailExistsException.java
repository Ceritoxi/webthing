package happy.model.user.register.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserWithSameEmailExistsException extends RuntimeException {

    public UserWithSameEmailExistsException(String message) {
        super(message);
    }
}
