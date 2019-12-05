package happy.web.user.register;

import happy.model.user.register.RegisterRequest;
import happy.service.user.register.RegisterService;
import happy.transfrom.user.register.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class RegisterController {
//
//    private UserTransformer userTransformer;
//    private RegisterService registerService;
//
//    @Autowired
//    public RegisterController(UserTransformer userTransformer, RegisterService registerService) {
//        this.userTransformer = userTransformer;
//        this.registerService = registerService;
//    }
//
//    @GetMapping("/register")
//    public String register() {
//        return "register";
//    }
//
//    @ModelAttribute("registerRequest")
//    public RegisterRequest registerRequest(RegisterRequest registerRequest) {
//        return new RegisterRequest();
//    }
//
//    @PostMapping("/register")
//    public String register(@Valid RegisterRequest registerRequest, RedirectAttributes redirectAttributes) {
//        registerService.registerUser(userTransformer.transformToUserCandidate(registerRequest));
//        return "redirect:/";
//    }
}
