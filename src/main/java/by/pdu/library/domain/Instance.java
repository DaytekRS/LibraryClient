package by.pdu.library.domain;

import lombok.Data;

@Data
public class Instance extends Edition{
    private Long numberInstance;

    public Instance() {
    }

    public Instance(Long numberInstance, Edition edition) {
        this.numberInstance = numberInstance;
        this.id = edition.id;
        this.image = edition.image;
        this.language = edition.language;
        this.name = edition.name;
        this.price = edition.price;
        this.publishingHouse = edition.publishingHouse;
        this.year = edition.year;
    }
}
