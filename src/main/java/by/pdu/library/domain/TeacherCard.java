package by.pdu.library.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherCard extends Card {
    {
        type = "Преподаватель";
    }

    private Department department;
    private Grade grade;
}
