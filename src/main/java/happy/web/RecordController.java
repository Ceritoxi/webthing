package happy.web;

import happy.domain.user.User;
import happy.model.record.BackFillRecordRequest;
import happy.service.record.RecordService;
import happy.service.user.login.TokenService;
import happy.transfrom.record.RecordTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Optional;

@RestController
public class RecordController {

    private TokenService tokenService;
    private RecordService recordService;
    private RecordTransformer recordTransformer;
    private String tokenCookieName;

    @Autowired
    public RecordController(TokenService tokenService, RecordService recordService, RecordTransformer recordTransformer, @Value("${token.name}") String tokenCookieName) {
        this.tokenService = tokenService;
        this.recordService = recordService;
        this.recordTransformer = recordTransformer;
        this.tokenCookieName = tokenCookieName;
    }

    @PostMapping("/addRecordsWithNoCare")
    public ResponseEntity addRecordsWithNoCare(@RequestBody BackFillRecordRequest records, HttpServletRequest request) {
        Optional<Cookie> tokenCookie = Arrays.stream(request.getCookies() != null ? request.getCookies() : new Cookie[0]).filter(cookie -> cookie.getName().equals(tokenCookieName)).findFirst();
        if (tokenCookie.isPresent()) {
            Optional<User> user = tokenService.getByToken(tokenCookie.get().getValue());
            if (user.isPresent()) {
                recordService.addRecords(recordTransformer.transformToRecords(records, user.get()));
            } else {
                throw new RuntimeException("RIP");
            }
        } else {
            throw new RuntimeException("RIP");
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
