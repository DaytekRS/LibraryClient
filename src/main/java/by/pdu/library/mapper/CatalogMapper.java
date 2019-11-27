package by.pdu.library.mapper;

import by.pdu.library.domain.Catalog;

import java.util.List;

public interface CatalogMapper {
    List<Catalog> getRootCatalog();

    List<Catalog> getCatalogByRoot(String id);
}
