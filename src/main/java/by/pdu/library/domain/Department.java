package by.pdu.library.domain;

import lombok.Data;

@Data
public class Department {
    private Long id;
    private String name;
    private Faculty faculty;
}
