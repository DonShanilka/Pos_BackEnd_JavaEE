package lk.ijse.pos_back_end.BO.Bo;

import lk.ijse.pos_back_end.Dto.OrderDetailDto;

import java.sql.Connection;

public interface OrderDetailsBO {
    boolean saveOrderDetailsBO(OrderDetailDto detailDto, Connection connection);
}
