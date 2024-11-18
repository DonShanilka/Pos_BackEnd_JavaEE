
package lk.ijse.pos_back_end.BO.Impl;

import lk.ijse.pos_back_end.BO.Bo.OrderBO;
import lk.ijse.pos_back_end.DAO.Dao.OrderDAO;
import lk.ijse.pos_back_end.DAO.Impl.OrderDAOImpl;
import lk.ijse.pos_back_end.Dto.OrderDto;
import lk.ijse.pos_back_end.Entity.Order;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = new OrderDAOImpl();
    @Override
    public boolean saveOrderBO(OrderDto orderDto, Connection connection) throws SQLException {
        return orderDAO.save(new Order(
                orderDto.getOrderId(),
                orderDto.getCusId(),
                orderDto.getDate()
        ),connection);
    }
}
