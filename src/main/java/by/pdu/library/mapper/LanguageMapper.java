package by.pdu.library.mapper;

import by.pdu.library.domain.Language;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LanguageMapper {
    Language getLanguageById(Integer id);

    List getLanguage();

    void insertLanguage(String name);

    void removeLanguage(Long id);

    void updateLanguage(@Param("id") Long id, @Param("name") String name);
}
