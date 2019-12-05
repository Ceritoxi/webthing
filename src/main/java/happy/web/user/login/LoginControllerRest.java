package happy.web.user.login;

import happy.domain.user.Token;
import happy.model.user.login.LoginRequest;
import happy.service.user.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;

@RestController
public class LoginControllerRest {

    private LoginService loginService;
    private String tokenCookieName;

    @Autowired
    public LoginControllerRest(LoginService loginService, @Value("${token.name}") String tokenCookieName) {
        this.loginService = loginService;
        this.tokenCookieName = tokenCookieName;
    }

    @GetMapping("/login")
    ResponseEntity<String> loginString() {
        return ResponseEntity.ok("ok, login");
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid LoginRequest loginRequest) {
        Token token = loginService.login(loginRequest);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Set-Cookie", tokenCookieName + "=" + token.getToken() + "; Max-Age=" + getMaxAge(token) + "; Path=/; HttpOnly");
        return new ResponseEntity(headers, HttpStatus.NO_CONTENT);
    }

    private int getMaxAge(Token token) {
        return (int) Duration.between(LocalDateTime.now(), token.getExpiryDate()).getSeconds();
    }
}
