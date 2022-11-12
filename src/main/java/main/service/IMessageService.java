package main.service;

import main.dto.DtoMessage;
import main.model.Message;

import java.util.List;
import java.util.Map;

public interface IMessageService {
    Map<String, Boolean> sendMessage(String message);
    List<DtoMessage> getAllMessage();

}
