package by.pdu.library.mapper;

import by.pdu.library.domain.Language;

import java.util.List;

public interface LanguageMapper {
    Language getLanguageById(Integer id);

    List getLanguage();

    void insertLanguage(String name);
}
