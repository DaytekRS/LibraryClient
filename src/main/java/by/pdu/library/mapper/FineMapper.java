package by.pdu.library.mapper;

import by.pdu.library.domain.Fine;

import java.util.List;


public interface FineMapper {
    List<Fine> getFine();

    void insertFine(String name, Float price);
}
