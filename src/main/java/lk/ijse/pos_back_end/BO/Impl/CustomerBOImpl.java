
package lk.ijse.pos_back_end.BO.Impl;

import lk.ijse.pos_back_end.BO.Bo.CustomerBO;
import lk.ijse.pos_back_end.DAO.Dao.CustomerDAO;
import lk.ijse.pos_back_end.DAO.Impl.CustomerDAOImpl;
import lk.ijse.pos_back_end.Dto.CustomerDto;
import lk.ijse.pos_back_end.Entity.Customer;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = new CustomerDAOImpl();
    @Override
    public String saveBOCustomer(CustomerDto customerDto, Connection connection) {
        return customerDAO.saveCustomer(new Customer(
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getAddress(),
                customerDto.getSalary()
        ),connection);
    }

    @Override
    public String updateCustomer(CustomerDto customerDto, Connection connection) {
        return customerDAO.updateCustomer(new Customer(
                customerDto.getId(),
                customerDto.getName(),
                customerDto.getAddress(),
                customerDto.getSalary()
        ),connection);
    }

    @Override
    public String deleteCustomer(String id, Connection connection) {
        return customerDAO.deleteCustomer(id,connection);
    }

    @Override
    public List<CustomerDto> getAllCustomers(Connection connection) {
        List<Customer>customerList = customerDAO.getAllCustomer(connection);
        List<CustomerDto>customerDtoList = new ArrayList<>();

        for (Customer customer:customerList) {
            customerDtoList.add(new CustomerDto(
                    customer.getId(),
                    customer.getName(),
                    customer.getAddress(),
                    customer.getSalary()
            ));
        }
        return customerDtoList;
    }
}