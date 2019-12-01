package by.pdu.library.domain;

import lombok.Data;

@Data
public class Article {
    private Long id;
    private String name;
    private Catalog catalog;
}
