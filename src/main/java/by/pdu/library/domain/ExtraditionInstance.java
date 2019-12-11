package by.pdu.library.domain;

import lombok.Data;

@Data
public class ExtraditionInstance extends Extradition{
    private Edition edition;
    private Long instanceNumber;
}
