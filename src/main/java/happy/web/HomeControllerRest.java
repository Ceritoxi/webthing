package happy.web;

import happy.domain.user.User;
import happy.model.record.Statistics;
import happy.service.record.statistics.StatisticsAssemblerService;
import happy.service.user.login.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class HomeControllerRest {

    private TokenService tokenService;
    private StatisticsAssemblerService statisticsAssemblerService;
    private String tokenCookieName;

    public HomeControllerRest(TokenService tokenService, @Value("${token.name}") String tokenCookieName) {
        this.tokenService = tokenService;
        this.tokenCookieName = tokenCookieName;
    }

//    @ModelAttribute("statistics")
//    public Statistics statistics(String dateTime, String userToken) {
//        return statisticsAssemblerService.assembleStatisticsForUser(ZonedDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME), tokenService.getByToken(userToken).orElseThrow(IllegalArgumentException::new));
//    }

    @GetMapping("/")
    public ResponseEntity<String> getStatistics(@RequestParam String dateTime) {
        return ResponseEntity.ok("ok, home");
    }
}
