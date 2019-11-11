package by.pdu.library.mapper;

import by.pdu.library.domain.ReadingRoom;

import java.util.List;

public interface ReadingRoomMapper {
    List<ReadingRoom> getReadingRoom();

    void insertReadingRoom(String name, String address);
}
