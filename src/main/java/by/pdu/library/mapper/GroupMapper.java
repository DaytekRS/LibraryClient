package by.pdu.library.mapper;

import by.pdu.library.domain.Group;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface GroupMapper {
    List<Group> getGroup();

    void insertGroup(@Param("name") String name, @Param("facultyId")Long facultyId);

    void updateGroup(@Param("id") Long id, @Param("name") String name, @Param("facultyId")Long facultyId);

    void dropGroup(@Param("id") Long id);
}
