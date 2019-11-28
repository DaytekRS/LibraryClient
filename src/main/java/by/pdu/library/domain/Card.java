package by.pdu.library.domain;

import lombok.Data;

import java.sql.Date;

@Data
abstract class Card {
    protected Long id;
    protected String FIO;
    protected Date birthday;
    protected Date issueDate;
    protected Date validDate;
    protected User user;
}
