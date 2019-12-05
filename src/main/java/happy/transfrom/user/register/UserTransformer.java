package happy.transfrom.user.register;

import happy.domain.user.PersonalConfigurations;
import happy.util.SecurityUtil;
import happy.domain.user.Credentials;
import happy.domain.user.User;
import happy.model.user.register.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class UserTransformer {

    public User transformToUserCandidate(RegisterRequest registerRequest) {
        Credentials credentials = SecurityUtil.generateHashWithSalsa(registerRequest.getPassword());
        User result = new User();
        result.setName(registerRequest.getName());
        result.setEmail(registerRequest.getEmail());
        result.setHash(credentials.getHash());
        result.setSalt(credentials.getSalt());
        result.setPersonalConfigurations(PersonalConfigurations.createDefault());
        return result;
    }


}
