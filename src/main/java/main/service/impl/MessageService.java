package main.service.impl;

import main.dto.DtoMessage;
import main.dto.MessageMapper;
import main.model.Message;
import main.model.User;
import main.repository.MessageRepository;
import main.repository.UserRepository;
import main.service.IMessageService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Service
public class MessageService implements IMessageService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Map<String, Boolean> sendMessage(String message) {
        if (Strings.isEmpty(message)) {
            return Map.of("result", false);
        }

        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        User userOpt = userRepository.findBySessionId(sessionId).get();
        Message msg = new Message(LocalDateTime.now(), userOpt, message);
        messageRepository.save(msg);
        return Map.of("result", true);
    }

    @Override
    public List<DtoMessage> getAllMessage() {
        return messageRepository
                .findAll(Sort.by(Sort.Direction.ASC, "dateTime"))
                .stream()
                .map(MessageMapper::map)
                .collect(Collectors.toList());
    }


}
