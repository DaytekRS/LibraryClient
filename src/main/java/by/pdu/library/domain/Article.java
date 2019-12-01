package by.pdu.library.domain;

import lombok.Data;

import java.util.List;
import java.util.Objects;

@Data
public class Article {
    private Long id;
    private String name;
    private Catalog catalog;
    private List<Author> authors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id);
    }

}
