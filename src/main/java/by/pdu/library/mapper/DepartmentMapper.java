package by.pdu.library.mapper;

import by.pdu.library.domain.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper {
    List<Department> getDepartment();

    void insertDepartment(@Param("name") String name, @Param("facultyId")Long facultyId);

    void updateDepartment(@Param("id") Long id, @Param("name") String name, @Param("facultyId")Long facultyId);

    void dropDepartment(@Param("id") Long id);
}
