package happy.web;

import happy.domain.user.User;
import happy.model.record.Statistics;
import happy.service.record.statistics.RecordStatisticsService;
import happy.service.record.statistics.StatisticsAssemblerService;
import happy.service.user.login.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class HomeController {

//    private TokenService tokenService;
//    private StatisticsAssemblerService statisticsAssemblerService;
//    private String tokenCookieName;
//
//    public HomeController(TokenService tokenService, @Value("${token.name}") String tokenCookieName) {
//        this.tokenService = tokenService;
//        this.tokenCookieName = tokenCookieName;
//    }
//
//    @ModelAttribute("statistics")
//    public Statistics statistics(String dateTime, String userToken) {
//        return statisticsAssemblerService.assembleStatisticsForUser(ZonedDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME), tokenService.getByToken(userToken).orElseThrow(IllegalArgumentException::new));
//    }
//
//    @RequestMapping("/")
//    public String helloWorld(RedirectAttributes redirectAttributes, HttpServletRequest httpServletRequest) {
//        //this whole thing should be in a filter xD
//        Optional<Cookie> tokenCookie = Arrays.stream(httpServletRequest.getCookies()).filter(cookie -> cookie.getName().equals(tokenCookieName)).findFirst();
//        if (tokenCookie.isPresent()) {
//            Optional<User> user = tokenService.getByToken(tokenCookie.get().getValue());
//            if (user.isPresent()) {
//            } else {
//                return "redirect:/login";
//            }
//        } else {
//            return "redirect:/login";
//        }
//        return "welcome";
//    }
}
