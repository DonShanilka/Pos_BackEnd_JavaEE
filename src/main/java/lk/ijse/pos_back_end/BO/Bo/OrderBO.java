package lk.ijse.pos_back_end.BO.Bo;

import lk.ijse.pos_back_end.Dto.OrderDto;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderBO {
    boolean saveOrderBO(OrderDto orderDto, Connection connection) throws SQLException;
}
