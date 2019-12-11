package by.pdu.library.domain;

import lombok.Data;

@Data
public class Order {
    private Long id;
    private Edition edition;
    private Employe employe;
    private String status;
    private Long cardId;
    {
        status = "Не собран";
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
