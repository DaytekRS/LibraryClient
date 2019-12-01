package by.pdu.library.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class Periodic extends Edition {
    private Long number;
    private Integer month;
    private List<Article> articles;

    public Periodic(){

    }

    public Periodic(Long id, Integer year, String image, String name, Language language, Float price, PublishingHouse publishingHouse, Long number, Integer month, List<Article> articles) {
        super(id, year, image, name, language, price, publishingHouse);
        this.number = number;
        this.month = month;
        this.articles = articles;
    }
}
