package main.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DtoMessage {

    private String dateTime;

    private String username;

    private String text;

    public DtoMessage() {
    }

    public DtoMessage(String datetime, String username, String text) {
        this.dateTime = datetime;
        this.username = username;
        this.text = text;
    }


}
