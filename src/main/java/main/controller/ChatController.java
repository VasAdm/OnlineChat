package main.controller;


import main.dto.DtoMessage;
import main.model.User;
import main.service.IMessageService;
import main.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ChatController {
    @Autowired
    @Qualifier("userService")
    private IUserService userService;
    @Autowired
    @Qualifier("messageService")
    private IMessageService messageService;

    @GetMapping("/init")
    public Map<String, Boolean> init() {
        return userService.isAuthorised();
    }

    @PostMapping("/auth")
    public Map<String, Boolean> auth(@RequestParam String name) {
        return userService.auth(name);
    }

    @PostMapping("/messages")
    public Map<String, Boolean> sendMessage(@RequestParam String message) {
        return messageService.sendMessage(message);
    }

    @GetMapping("/messages")
    public List<DtoMessage> getMessagesList() {
        return messageService.getAllMessage();
    }

    @GetMapping("/users")
    public List<User> getUsersList() {
        return userService.getUsers();
    }
}
