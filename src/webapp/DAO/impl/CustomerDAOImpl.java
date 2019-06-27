package webapp.DAO.impl;

import webapp.DAO.CustomerDAO;
import webapp.entity.Customer;
import webapp.listener.ContextListener;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    private DataSource pool;

    public CustomerDAOImpl() {
        ServletContext servletContext = ContextListener.getServletContext();
        if(servletContext.getAttribute("pool") != null) {
            pool = (DataSource) servletContext.getAttribute("pool");
        }
    }

    @Override
    public boolean createCustomer(Customer customer) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;

        try {
            connection= pool.getConnection();
            preparedStatement=connection.prepareStatement("INSERT INTO customers values (?,?,?);");
            preparedStatement.setString(1,customer.getCusId());
            preparedStatement.setString(2,customer.getCusName());
            preparedStatement.setString(3,customer.getCusAddress());
            int i = preparedStatement.executeUpdate();
            return (i>0);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public List<Customer> getAllCustomers() {
        Connection connection = null;
        PreparedStatement preparedStatement=null;

        try {
            connection= pool.getConnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM customers;");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Customer> customerList = new ArrayList<>();
            while (resultSet.next()){
                customerList.add(new Customer(resultSet.getString("cusId"),
                        resultSet.getString("cusName"),
                        resultSet.getString("cusAddress")));
            }
            return customerList;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;

        try {
            connection= pool.getConnection();
            preparedStatement=connection.prepareStatement("UPDATE customers SET cusName = ?, cusAddress = ? where cusId = ?;");
            preparedStatement.setString(1,customer.getCusName());
            preparedStatement.setString(2,customer.getCusAddress());
            preparedStatement.setString(3,customer.getCusId());
            int i = preparedStatement.executeUpdate();
            return (i>0);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(String cusId) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;

        try {
            connection= pool.getConnection();
            preparedStatement=connection.prepareStatement("DELETE FROM customers where cusId = ?;");
            preparedStatement.setString(1,cusId);
            int i = preparedStatement.executeUpdate();
            return (i>0);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
