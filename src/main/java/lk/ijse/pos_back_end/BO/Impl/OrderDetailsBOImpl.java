
package lk.ijse.pos_back_end.BO.Impl;

import lk.ijse.pos_back_end.BO.Bo.OrderDetailsBO;
import lk.ijse.pos_back_end.DAO.Dao.OrderDetailsDAO;
import lk.ijse.pos_back_end.DAO.Impl.OrderDetailDAOImpl;
import lk.ijse.pos_back_end.Dto.OrderDetailDto;
import lk.ijse.pos_back_end.Entity.OrderDetail;

import java.sql.Connection;

public class OrderDetailsBOImpl implements OrderDetailsBO {
    OrderDetailsDAO orderDetailsDAO = new OrderDetailDAOImpl();
    @Override
    public boolean saveOrderDetailsBO(OrderDetailDto detailDto, Connection connection) {
        return orderDetailsDAO.save(new OrderDetail(
                detailDto.getOrderId(),
                detailDto.getCusId(),
                detailDto.getProId(),
                detailDto.getQtyOnHand(),
                detailDto.getUnitPrice()
        ),connection);
    }
}
