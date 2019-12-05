package happy.service.user.register;

import happy.domain.user.User;
import happy.model.user.register.exception.UserWithSameEmailExistsException;
import happy.repository.user.UserRepository;
import happy.transfrom.user.register.UserTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RegisterService {

    private UserRepository userRepository;
    private UserTransformer userTransformer;

    @Autowired
    public RegisterService(UserRepository userRepository, UserTransformer userTransformer) {
        this.userRepository = userRepository;
        this.userTransformer = userTransformer;
    }

    public void registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new UserWithSameEmailExistsException(String.format("User with email %s already exists!", user.getEmail()));
        } else {
            userRepository.save(user);
        }
    }
}
