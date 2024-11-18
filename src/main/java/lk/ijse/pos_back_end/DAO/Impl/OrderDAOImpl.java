
package lk.ijse.pos_back_end.DAO.Impl;

import lk.ijse.pos_back_end.DAO.Dao.OrderDAO;
import lk.ijse.pos_back_end.Entity.Order;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDAOImpl implements OrderDAO {
    static String SAVE_ORDER = "INSERT INTO orders(order_id,cus_id,date) VALUES(?,?,?)";
    @Override
    public boolean save(Order order, Connection connection) throws SQLException {
        try{
            var ps = connection.prepareStatement(SAVE_ORDER);
            ps.setString(1,order.getOrderId());
            ps.setString(2,order.getCusId());
            ps.setString(3, String.valueOf(order.getDate()));

            if (ps.executeUpdate() !=0){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
