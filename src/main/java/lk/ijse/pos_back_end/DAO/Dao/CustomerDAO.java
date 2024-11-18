package lk.ijse.pos_back_end.DAO.Dao;

import lk.ijse.pos_back_end.Dto.CustomerDto;
import lk.ijse.pos_back_end.Entity.Customer;

import java.sql.Connection;
import java.util.List;

public interface CustomerDAO {
    String saveCustomer(Customer customer, Connection connection);
    String deleteCustomer(String id, Connection connection);
    String updateCustomer(Customer customer, Connection connection);
    List<Customer> getAllCustomer(Connection connection);
}
