package by.pdu.library.domain;

import lombok.Data;

@Data
public class Edition{
    public Edition(){

    }
    public Edition(Long id, Integer year, String image, String name, Language language, Float price, PublishingHouse publishingHouse) {
        this.id = id;
        this.year = year;
        this.image = image;
        this.name = name;
        this.language = language;
        this.price = price;
        this.publishingHouse = publishingHouse;
    }

    protected Long id;
    protected Integer year;
    protected String image;
    protected String name;
    protected Language language;
    protected Float price;
    protected PublishingHouse publishingHouse;

}

