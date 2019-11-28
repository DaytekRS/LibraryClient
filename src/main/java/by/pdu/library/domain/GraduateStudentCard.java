package by.pdu.library.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class GraduateStudentCard extends Card {
    private String scientificTopic;
}
