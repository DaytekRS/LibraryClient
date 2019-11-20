package by.pdu.library.mapper;

import by.pdu.library.domain.ReadingRoom;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ReadingRoomMapper {
    List<ReadingRoom> getReadingRoom();

    void insertReadingRoom(@Param("name") String name, @Param("address") String address);

    void removeReadingRoom(Long id);

    void updateReadingRoom(@Param("id") Long id, @Param("name") String name, @Param("address") String address);

    void updateReadingRoom(ReadingRoom room);
}
