package by.pdu.library.domain;

import lombok.Data;

@Data
public class Employe {
    private Long id;
    private String login;
    private String FIO;
    private ReadingRoom room;
}
