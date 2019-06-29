package webapp.DAO.impl;

import webapp.DAO.OrdersDAO;
import webapp.entity.Orders;
import webapp.listener.ContextListener;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrdersDAOImpl implements OrdersDAO {
    private DataSource pool;

    public OrdersDAOImpl() {
        ServletContext servletContext = ContextListener.getServletContext();
        if (servletContext.getAttribute("pool") != null) {
            pool = (DataSource) servletContext.getAttribute("pool");
        }
    }

    @Override
    public boolean placeOrder(Orders orders, Connection connection) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO orders values (?,?,?);");
            preparedStatement.setString(1, orders.getOrderId());
            preparedStatement.setString(2, orders.getOrderDate());
            preparedStatement.setString(3, orders.getCustomer());
            int i = preparedStatement.executeUpdate();
            return (i > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
}
