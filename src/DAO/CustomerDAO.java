package DAO;

import factory.DatabaseConnection;
import model.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class CustomerDAO {
    // Data object access, where we'll put the business roles

    public void save(Customer customer) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO customer (name, email, phone, created_at) VALUES (?, ?, ?, ?)";

        try {
            // Creating a database connection
            connection = DatabaseConnection.getInstance().getConnection();

            // Creating a PrepareStatement to execute a query
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getEmail());
            preparedStatement.setString(3, customer.getPhone());
            preparedStatement.setDate(4, new Date(new java.util.Date().getTime()));

            preparedStatement.execute();

            System.out.println("Customer " + customer.getName() + " saved with success");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> selectAll() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String sql = "SELECT * FROM customer";

        List<String> customersList = new ArrayList<>();

        // Responsible to recovery database values
        ResultSet resultSet = null;

        try {
            // Creating a database connection
            connection = DatabaseConnection.getInstance().getConnection();
            // Creating a PrepareStatement to execute a query
            preparedStatement = connection.prepareStatement(sql);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Customer currentCustomer = new Customer();

                customersList.add(
                        resultSet.getInt("id_customer") + " " +
                        resultSet.getString("name")     + " " +
                        resultSet.getString("email")    + " " +
                        resultSet.getString("phone")    + " " +
                        resultSet.getDate("created_at")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customersList;
    }
}

