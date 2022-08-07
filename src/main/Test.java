package main;

import DAO.CustomerDAO;
import factory.DatabaseConnection;
import model.Customer;

import java.sql.*;

public class Test {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // createCustomer();
        selectAllCustomer();
    }

    private static void createCustomer() {
        Customer customer = new Customer();

        customer.setName("João");
        customer.setEmail("joao@yahoo.com");
        customer.setPhone("19 9999-1949");

        CustomerDAO customerDAO = new CustomerDAO();
        customerDAO.save(customer);
    }

    private static void selectAllCustomer() {
        CustomerDAO customerDAO = new CustomerDAO();
        System.out.println(customerDAO.selectAll());
    }
}
