package happy.web.user.register;

import happy.model.user.register.RegisterRequest;
import happy.service.user.register.RegisterService;
import happy.transfrom.user.register.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class RegisterControllerRest {

    private UserTransformer userTransformer;
    private RegisterService registerService;

    @Autowired
    public RegisterControllerRest(UserTransformer userTransformer, RegisterService registerService) {
        this.userTransformer = userTransformer;
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterRequest registerRequest) {
        registerService.registerUser(userTransformer.transformToUserCandidate(registerRequest));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
