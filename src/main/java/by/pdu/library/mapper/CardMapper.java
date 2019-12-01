package by.pdu.library.mapper;

import by.pdu.library.domain.GraduateStudentCard;
import by.pdu.library.domain.OtherCard;
import by.pdu.library.domain.StudentCard;
import by.pdu.library.domain.TeacherCard;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CardMapper {
    List<StudentCard> getStudentCard();

    List<TeacherCard> getTeacherCard();

    List<GraduateStudentCard> getGraduateStudentCard();

    List<OtherCard> getOtherCard();

    void createStudentCard(@Param("login") String login,
                           @Param("password") String password,
                           @Param("email") String email,
                           @Param("fio") String fio,
                           @Param("birthday") String birthday,
                           @Param("validDate") String validDate,
                           @Param("groupId") Long groupId);

    void createTeacherCard(@Param("login") String login,
                           @Param("password") String password,
                           @Param("email") String email,
                           @Param("fio") String fio,
                           @Param("birthday") String birthday,
                           @Param("validDate") String validDate,
                           @Param("departmentId") Long departmentId,
                           @Param("gradeId") Long gradeId);

    void createGraduateStudent(@Param("login") String login,
                               @Param("password") String password,
                               @Param("email") String email,
                               @Param("fio") String fio,
                               @Param("birthday") String birthday,
                               @Param("validDate") String validDate,
                               @Param("scientificTopic") String scientificTopic);

    void createOther(@Param("login") String login,
                     @Param("password") String password,
                     @Param("email") String email,
                     @Param("fio") String fio,
                     @Param("birthday") String birthday,
                     @Param("validDate") String validDate,
                     @Param("registration") String registration,
                     @Param("work") String work,
                     @Param("passport") String passport);

    void removeCard(Long id);

    Integer checkPassword(String password);

    void updateStudentCard(@Param("id") Long id,
                           @Param("fio") String fio,
                           @Param("birthday") String birthday,
                           @Param("validDate") String validDate,
                           @Param("groupId") Long groupId);

    void updateTeacherCard(@Param("id") Long id,
                           @Param("fio") String fio,
                           @Param("birthday") String birthday,
                           @Param("validDate") String validDate,
                           @Param("departmentId") Long departmentId,
                           @Param("gradeId") Long gradeId);

    void updateGraduateStudent(@Param("id") Long id,
                               @Param("fio") String fio,
                               @Param("birthday") String birthday,
                               @Param("validDate") String validDate,
                               @Param("scientificTopic") String scientificTopic);

    void updateOther(@Param("id") Long id,
                     @Param("fio") String fio,
                     @Param("birthday") String birthday,
                     @Param("validDate") String validDate,
                     @Param("registration") String registration,
                     @Param("work") String work,
                     @Param("passport") String passport);
}
