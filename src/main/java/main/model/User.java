package main.model;

import lombok.Data;


import javax.persistence.*;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String sessionId;
    private String name;

    public User() {}

    public User(String sessionId, String name) {
        this.sessionId = sessionId;
        this.name = name;
    }
}