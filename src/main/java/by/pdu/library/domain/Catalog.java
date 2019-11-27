package by.pdu.library.domain;

import lombok.Data;

@Data
public class Catalog {
    private String id;
    private String name;
    private Catalog root;

    @Override
    public String toString() {
        return id + " - " + name;
    }
}
