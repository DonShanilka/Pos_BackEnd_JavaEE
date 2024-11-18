package lk.ijse.pos_back_end.DAO.Dao;

import lk.ijse.pos_back_end.Dto.ItemDto;
import lk.ijse.pos_back_end.Entity.Item;

import java.sql.Connection;
import java.util.List;

public interface ItemDAO {
    String saveItem(Item item, Connection connection);
    String updateItem(Item item,Connection connection);
    String deleteItem(String id,Connection connection);
    List<Item>getAllItem(Connection connection);
}
