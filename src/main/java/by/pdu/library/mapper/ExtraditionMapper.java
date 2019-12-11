package by.pdu.library.mapper;

import by.pdu.library.domain.Extradition;
import by.pdu.library.domain.ExtraditionInstance;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExtraditionMapper {
    List<Extradition> getExtradition(Long roomId);

    List<ExtraditionInstance> getExtraditionInstance(@Param("roomId") Long roomId);

    List<ExtraditionInstance> getExtraditionInstance(@Param("editionId") Long editionId,@Param("roomId") Long roomId);

    void returnInstance(Long id);

    void takeInstance(@Param("employeId") Long employeId,@Param("date")String date,@Param("cardId")  Long cardId,@Param("instanceId")  Long instanceId);
}
