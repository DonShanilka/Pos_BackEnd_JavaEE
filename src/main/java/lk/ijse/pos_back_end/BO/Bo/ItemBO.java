package lk.ijse.pos_back_end.BO.Bo;

import lk.ijse.pos_back_end.Dto.ItemDto;

import java.sql.Connection;
import java.util.List;

public interface ItemBO {
    String saveItem(ItemDto itemDto, Connection connection);
    String updateItem(ItemDto itemDto,Connection connection);
    String deleteItem(String code,Connection connection);
    List<ItemDto> getAllItems(Connection connection);
}
