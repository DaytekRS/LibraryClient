package by.pdu.library.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class User {
    private Long cardId;
    private String login;
    private String password;
    private String email;
    private Date notValid;
}
