package by.pdu.library.mapper;

import by.pdu.library.domain.Grade;

import java.util.List;

public interface GradeMapper {
    List<Grade> getGrade();

    void insertGrade(String name);

    void removeGrade(Long id);

    void updateGrade(Grade grade);

    void updateGrade(Long id, String name);
}
