package by.pdu.library.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class Author {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return Objects.equals(id, author.id);
    }

}
