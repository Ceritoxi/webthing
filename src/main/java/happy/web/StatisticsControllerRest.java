package happy.web;

import happy.domain.user.User;
import happy.model.record.Statistics;
import happy.service.record.statistics.StatisticsAssemblerService;
import happy.service.user.login.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

@Controller
public class StatisticsControllerRest {

    private TokenService tokenService;
    private StatisticsAssemblerService statisticsAssemblerService;
    private String tokenCookieName;

    @Autowired
    public StatisticsControllerRest(TokenService tokenService, StatisticsAssemblerService statisticsAssemblerService, @Value("${token.name}") String tokenCookieName) {
        this.tokenService = tokenService;
        this.statisticsAssemblerService = statisticsAssemblerService;
        this.tokenCookieName = tokenCookieName;
    }

//    @ModelAttribute("statistics")
//    public Statistics statistics(String dateTime, String userToken) {
//        return statisticsAssemblerService.assembleStatisticsForUser(ZonedDateTime.parse(dateTime, DateTimeFormatter.ISO_DATE_TIME), tokenService.getByToken(userToken).orElseThrow(IllegalArgumentException::new));
//    }

    @GetMapping("/statistics")
    public ResponseEntity<Statistics> getStatistics(@RequestParam String zoneId, @RequestParam int year, @RequestParam int month, @RequestParam double percentile, HttpServletRequest httpServletRequest) {
        //this whole thing should be in a filter xD
        Optional<Cookie> tokenCookie = Arrays.stream(httpServletRequest.getCookies() != null ? httpServletRequest.getCookies() : new Cookie[0]).filter(cookie -> cookie.getName().equals(tokenCookieName)).findFirst();
        HttpHeaders headers = new HttpHeaders();
        if (tokenCookie.isPresent()) {
            Optional<User> user = tokenService.getByToken(tokenCookie.get().getValue());
            if (user.isPresent()) {
                return ResponseEntity.ok(statisticsAssemblerService.assembleStatisticsForUser(ZoneId.of(zoneId), year, month, percentile, user.get()));
            } else {
                headers.add("Location", "/login");
                return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
            }
        } else {
            headers.add("Location", "/login");
            return new ResponseEntity<>(headers, HttpStatus.SEE_OTHER);
        }
    }
}
