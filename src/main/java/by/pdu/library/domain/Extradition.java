package by.pdu.library.domain;

import lombok.Data;

@Data
public class Extradition {
    private Long id;
    protected Card card;
    protected Employe employe;
}
