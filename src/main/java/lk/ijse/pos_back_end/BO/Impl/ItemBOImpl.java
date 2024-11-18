
package lk.ijse.pos_back_end.BO.Impl;

import lk.ijse.pos_back_end.BO.Bo.ItemBO;
import lk.ijse.pos_back_end.DAO.Dao.ItemDAO;
import lk.ijse.pos_back_end.DAO.Impl.ItemDAOImpl;
import lk.ijse.pos_back_end.Dto.ItemDto;
import lk.ijse.pos_back_end.Entity.Item;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = new ItemDAOImpl();
    @Override
    public String saveItem(ItemDto itemDto, Connection connection) {
        return itemDAO.saveItem(new Item(
                itemDto.getCode(),
                itemDto.getItemName(),
                itemDto.getUnitPrice(),
                itemDto.getQtyOnHand()
        ),connection);
    }

    @Override
    public String updateItem(ItemDto itemDto, Connection connection) {
        return itemDAO.updateItem(new Item(
                itemDto.getCode(),
                itemDto.getItemName(),
                itemDto.getUnitPrice(),
                itemDto.getQtyOnHand()
        ),connection);
    }

    @Override
    public String deleteItem(String code, Connection connection) {
        return itemDAO.deleteItem(code,connection);
    }

    @Override
    public List<ItemDto> getAllItems(Connection connection) {
        List<Item>itemList = itemDAO.getAllItem(connection);
        List<ItemDto>itemDtoList  = new ArrayList<>();

        for (Item item:itemList) {
                itemDtoList.add(new ItemDto(
                        item.getCode(),
                        item.getItemName(),
                        item.getUnitPrice(),
                        item.getQtyOnHand()
                ));
        }
        return itemDtoList;
    }
}
