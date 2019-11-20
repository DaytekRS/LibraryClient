package by.pdu.library.mapper;

import by.pdu.library.domain.Fine;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface FineMapper {
    List<Fine> getFine();

    void insertFine(@Param("name") String name, @Param("price") Float price);

    void removeFine(Long id);

    void updateFine(@Param("id") Long id, @Param("name") String name, @Param("price") Float price);
}
