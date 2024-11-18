
package lk.ijse.pos_back_end.DAO.Impl;

import lk.ijse.pos_back_end.DAO.Dao.ItemDAO;
import lk.ijse.pos_back_end.Dto.ItemDto;
import lk.ijse.pos_back_end.Entity.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    static String ITEM_SAVED = "INSERT INTO item(item_code,item_name,item_price,item_qty) VALUES(?,?,?,?)";
    static String GET_ITEMS = "SELECT * FROM item";
    static String UPDATE_ITEMS = "UPDATE item SET item_name = ?,item_price = ?,item_qty = ? WHERE item_code = ?";
    static String DELETE_ITEMS = "DELETE FROM item WHERE item_code = ?";

    @Override
    public String saveItem(Item itemDto, Connection connection) {
        try {
            var ps = connection.prepareStatement(ITEM_SAVED);

            ps.setString(1, itemDto.getCode());
            ps.setString(2, itemDto.getItemName());
            ps.setDouble(3, itemDto.getUnitPrice());
            ps.setInt(4, itemDto.getQtyOnHand());


            if (ps.executeUpdate() != 0) {
                return "Item Saved";
            } else {
                return "Unsuccessful";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    @Override
    public String updateItem(Item itemDto, Connection connection) {
        try {
            var ps = connection.prepareStatement(UPDATE_ITEMS);

            ps.setString(1,itemDto.getItemName());
            ps.setString(2, String.valueOf(itemDto.getUnitPrice()));
            ps.setString(3, String.valueOf(itemDto.getQtyOnHand()));
            ps.setString(4,itemDto.getCode());

            if (ps.executeUpdate() !=0){
                return "Item Updated";
            }else {
                return "Updating Failed";
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteItem(String id, Connection connection) {
        try {
            var ps = connection.prepareStatement(DELETE_ITEMS);
            ps.setString(1,id);

            if (ps.executeUpdate() !=0){
                return "Item Delete";
            }else {
                return "Deleting failed";
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Item> getAllItem(Connection connection) {
        List<Item> items = new ArrayList<>();

        try {
            PreparedStatement ps = connection.prepareStatement(GET_ITEMS);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Item itemDto = new Item();
                itemDto.setCode(rs.getString("item_code"));
                itemDto.setItemName(rs.getString("item_name"));
                itemDto.setUnitPrice(rs.getInt("item_price"));
                itemDto.setQtyOnHand(rs.getInt("item_qty"));

                items.add(itemDto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return items;
    }


}
