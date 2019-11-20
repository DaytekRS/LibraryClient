package by.pdu.library.mapper;

import by.pdu.library.domain.Employe;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeMapper {
    List<Employe> getEmploye();

    Employe getEmployeByLogin(@Param("login") String login);

    void createEmploye(@Param("login") String login, @Param("password") String password);

    void insertEmploye(@Param("FIO") String FIO, @Param("roomID") Long roomID, @Param("login") String login);

    String getRole();

    void dropEmploye(@Param("login") String login);
}
