package by.pdu.library.domain;

import lombok.Data;

@Data
public class ReadingRoom {
    private Long id;
    private String name;
    private String address;

    @Override
    public String toString() {
        return name;
    }
}
