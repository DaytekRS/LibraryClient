package by.pdu.library.mapper;

import by.pdu.library.domain.Author;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthorMapper {
    List<Author> getAuthor();

    void insertAuthor(String name);

    void removeAuthor(Long id);

    void updateAuthor(@Param("id") Long id, @Param("name") String name);
}
