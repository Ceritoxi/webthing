package happy.web.user.login;

import happy.domain.user.Token;
import happy.model.user.login.LoginRequest;
import happy.service.user.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.Duration;
import java.time.LocalDateTime;

@Controller
public class LoginController {

//    private LoginService loginService;
//    private String tokenCookieName;
//
//    @Autowired
//    public LoginController(LoginService loginService, @Value("${token.name}") String tokenCookieName) {
//        this.loginService = loginService;
//        this.tokenCookieName = tokenCookieName;
//    }
//
//    @GetMapping("/login")
//    public String login() {
//        return "login";
//    }
//
//    @ModelAttribute("loginRequest")
//    public LoginRequest loginRequest(LoginRequest loginRequest) {
//        return new LoginRequest();
//    }
//
//    @PostMapping("/login")
//    public String login(@Valid LoginRequest loginRequest, RedirectAttributes redirectAttributes, HttpServletResponse response) {
//        Token token = loginService.login(loginRequest);
//        Cookie tokenCookie = new Cookie(tokenCookieName, token.getToken());
//        tokenCookie.setMaxAge((int) Duration.between(LocalDateTime.now(), token.getExpiryDate()).getSeconds());
//        response.addCookie(tokenCookie);
//        return "redirect:/";
//    }
}
