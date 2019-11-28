package by.pdu.library.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class StudentCard extends Card {
    private Group group;
}
