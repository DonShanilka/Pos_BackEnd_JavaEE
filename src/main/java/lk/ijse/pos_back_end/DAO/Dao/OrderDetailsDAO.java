/* Created By Sithira Roneth
 * Date :8/31/24
 * Time :22:36
 * Project Name :Pos_Back_End
 * */
package lk.ijse.pos_back_end.DAO.Dao;

import lk.ijse.pos_back_end.Entity.OrderDetail;

import java.sql.Connection;

public interface OrderDetailsDAO {
    boolean save(OrderDetail orderDetail, Connection connection);
}
