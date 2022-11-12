package main.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private LocalDateTime dateTime;
    private String message;

    public Message() {
    }

    public Message(LocalDateTime dateTime, User user, String message) {
        this.user = user;
        this.dateTime = dateTime;
        this.message = message;
    }
}
