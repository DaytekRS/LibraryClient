package by.pdu.library.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Book extends Edition{
    private String description;
    private Catalog catalog;
    private List<Author> authors;

    public Book(){

    }

    public Book(Long id, Integer year, String image, String name, Language language, Float price, PublishingHouse publishingHouse, String description, Catalog catalog, List<Author> authors) {
        super(id, year, image, name, language, price, publishingHouse);
        this.description = description;
        this.catalog = catalog;
        this.authors = authors;
    }
}
