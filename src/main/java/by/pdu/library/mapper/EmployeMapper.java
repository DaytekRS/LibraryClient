package by.pdu.library.mapper;

import by.pdu.library.domain.Employe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeMapper {
    List<Employe> getEmploye();

    Employe getEmployeByLogin(@Param("login") String login);

    void createEmploye(@Param("login") String login,@Param("password") String password);

    String getRole();
}
