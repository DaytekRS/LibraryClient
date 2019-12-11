package by.pdu.library.mapper;

import by.pdu.library.domain.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    List<Order> getOrder(Long readingRoomId);

    void updateEmployeeOrder(@Param("id") Long id,@Param("employeId") Long employeId );

    void updateNoAssembledOrder(@Param("id") Long id);

    void deleteOrder(@Param("id") Long id);
}
