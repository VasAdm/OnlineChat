package main.service.impl;

import main.model.User;
import main.repository.UserRepository;
import main.service.IUserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Map<String, Boolean> auth(String name) {
        Map<String, Boolean> result = new HashMap<>();
        if (!Strings.isNotEmpty(name)) {
            result.put("result", false);
        } else {
            String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
            User user = new User(sessionId, name);
            userRepository.save(user);
            result.put("result", true);
        }

        return result;
    }

    @Override
    public Map<String, Boolean> isAuthorised() {
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        Optional<User> userOpt = userRepository.findBySessionId(sessionId);
        return Map.of("result", userOpt.isPresent());
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
