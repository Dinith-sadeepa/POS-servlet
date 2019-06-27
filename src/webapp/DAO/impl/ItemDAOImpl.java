package webapp.DAO.impl;

import webapp.DAO.ItemDAO;
import webapp.entity.Item;
import webapp.listener.ContextListener;

import javax.servlet.ServletContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {

    private DataSource pool;

    public ItemDAOImpl() {
        ServletContext servletContext = ContextListener.getServletContext();
        if (servletContext.getAttribute("pool") != null) {
            pool = (DataSource) servletContext.getAttribute("pool");
        }
    }

    @Override
    public boolean createItem(Item item) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = pool.getConnection();
            preparedStatement = connection.prepareStatement("INSERT INTO items values (?,?,?,?);");
            preparedStatement.setString(1, item.getItemCode());
            preparedStatement.setString(2, item.getItemName());
            preparedStatement.setInt(3, item.getItemQty());
            preparedStatement.setDouble(4, item.getItemPrice());
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
            if (connection != null) {
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
    public boolean updateItem(Item item) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;

        try {
            connection= pool.getConnection();
            preparedStatement=connection.prepareStatement("UPDATE items SET itemName = ?, itemQty = ?, itemPrice = ? where itemCode = ?;");
            preparedStatement.setString(1,item.getItemName());
            preparedStatement.setInt(2,item.getItemQty());
            preparedStatement.setDouble(3,item.getItemPrice());
            preparedStatement.setString(4,item.getItemCode());
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
    public List<Item> getAllItems() {
        Connection connection = null;
        PreparedStatement preparedStatement=null;

        try {
            connection= pool.getConnection();
            preparedStatement=connection.prepareStatement("SELECT * FROM items;");
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Item> itemList = new ArrayList<>();
            while (resultSet.next()){
                itemList.add(new Item(resultSet.getString("itemCode"),
                        resultSet.getString("itemName"),
                        resultSet.getInt("itemQty"),
                        resultSet.getDouble("itemPrice")));
            }
            return itemList;
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
    public boolean deleteItem(String itemCode) {
        Connection connection = null;
        PreparedStatement preparedStatement=null;

        try {
            connection= pool.getConnection();
            preparedStatement=connection.prepareStatement("DELETE FROM items where itemCode = ?;");
            preparedStatement.setString(1,itemCode);
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
