package by.pdu.library.domain;

import lombok.Data;

@Data
public class Grade {
    private Long id;
    private String name;

    @Override
    public String toString(){
        return name;
    }
}
