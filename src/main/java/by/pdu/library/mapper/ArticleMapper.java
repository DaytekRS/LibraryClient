package by.pdu.library.mapper;

import by.pdu.library.domain.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleMapper {
    List<Article> getArticle();

    void insertArticle(@Param("id") Long id, @Param("name") String name, @Param("catalogId") String catalogId);

    void removeArticle(Long id);

    void updateArticle(@Param("id") Long id, @Param("name") String name, @Param("catalogId") String catalogId);

    void insertAuthor(@Param("authorId") Long authorId, @Param("articleId") Long articleID);

    void removeAllAuthor(Long id);

    Long nextId();
}
