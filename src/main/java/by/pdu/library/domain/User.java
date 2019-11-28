package by.pdu.library.domain;

import lombok.Data;

import java.sql.Date;

@Data
public class User {
    private String login;
    private String password;
    private String email;
    private Date notValid;
}
