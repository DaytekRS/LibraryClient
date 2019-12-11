package by.pdu.library.domain;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private Edition edition;
    private Employe employe;
    private String status;
    {
        status = "Не собран";
    }
}
