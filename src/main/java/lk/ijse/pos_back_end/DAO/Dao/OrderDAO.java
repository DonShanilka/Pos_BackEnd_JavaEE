package lk.ijse.pos_back_end.DAO.Dao;

import lk.ijse.pos_back_end.Entity.Order;

import java.sql.Connection;
import java.sql.SQLException;

public interface OrderDAO {
    boolean save(Order order, Connection connection) throws SQLException;
}
