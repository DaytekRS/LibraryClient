package by.pdu.library.mapper;

import by.pdu.library.domain.PublishingHouse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PublishingHouseMapper {
    List<PublishingHouse> getPublishingHouse();

    void insertPublishingHouse(String name);

    void updatePublishingHouse(@Param("id") Long id, @Param("name") String name);

    void removePublishingHouse(Long id);
}
