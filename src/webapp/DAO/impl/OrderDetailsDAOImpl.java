package webapp.DAO.impl;

import webapp.DAO.OrderDetailsDAO;
import webapp.entity.OrderDetails;
import webapp.listener.ContextListener;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    private DataSource pool;

    public OrderDetailsDAOImpl() {
        ServletContext servletContext = ContextListener.getServletContext();
        if (servletContext.getAttribute("pool") != null) {
            pool = (DataSource) servletContext.getAttribute("pool");
        }
    }

    @Override
    public boolean placeOrderDetails(OrderDetails orderDetails, Connection connection) {
        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement("INSERT INTO orderDetails values (?,?,?,?);");
            preparedStatement.setString(1, orderDetails.getOrderId());
            preparedStatement.setString(2, orderDetails.getItemCode());
            preparedStatement.setLong(3, orderDetails.getOrderQty());
            preparedStatement.setDouble(4, orderDetails.getOrderPrice());
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
