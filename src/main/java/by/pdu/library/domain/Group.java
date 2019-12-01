package by.pdu.library.domain;

import lombok.Data;

@Data
public class Group {
    private Long id;
    private String name;
    private Faculty faculty;

    @Override
    public String toString() {
        return name;
    }
}
