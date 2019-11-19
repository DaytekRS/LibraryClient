package by.pdu.library.mapper;

import by.pdu.library.domain.Faculty;

import java.util.List;

public interface FacultyMapper {
    List<Faculty> getFaculty();

    void insertFaculty(String name);

    void updateFaculty(Faculty faculty);

    void updateFaculty(Long id, String name);

    void removeFaculty(Long id);
}
