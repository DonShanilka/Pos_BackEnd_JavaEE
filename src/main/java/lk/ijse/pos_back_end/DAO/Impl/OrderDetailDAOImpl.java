
package lk.ijse.pos_back_end.DAO.Impl;

import lk.ijse.pos_back_end.DAO.Dao.OrderDetailsDAO;
import lk.ijse.pos_back_end.Entity.OrderDetail;

import java.sql.Connection;
import java.sql.SQLException;

public class OrderDetailDAOImpl implements OrderDetailsDAO {
    static String SAVE_DETAILS = "INSERT INTO orderDetail(order_id,cus_id,pro_id,qty,price) VALUES(?,?,?,?,?)";
    @Override
    public boolean save(OrderDetail orderDetail, Connection connection) {
        try {
            var ps = connection.prepareStatement(SAVE_DETAILS);
            ps.setString(1,orderDetail.getOrderId());
            ps.setString(2,orderDetail.getId());
            ps.setString(3,orderDetail.getProId());
            ps.setString(4, String.valueOf(orderDetail.getQtyOnHand()));
            ps.setString(5, String.valueOf(orderDetail.getUnitPrice()));

            if (ps.executeUpdate() !=0){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
