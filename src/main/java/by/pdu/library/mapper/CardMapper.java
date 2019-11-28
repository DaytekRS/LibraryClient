package by.pdu.library.mapper;

import by.pdu.library.domain.GraduateStudentCard;
import by.pdu.library.domain.OtherCard;
import by.pdu.library.domain.StudentCard;
import by.pdu.library.domain.TeacherCard;

import java.util.List;

public interface CardMapper {
    List<StudentCard> getStudentCard();

    List<TeacherCard> getTeacherCard();

    List<GraduateStudentCard> getGraduateStudentCard();

    List<OtherCard> getOtherCard();
}
