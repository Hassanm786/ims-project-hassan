package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderItemDAO  {
    public static final Logger LOGGER = LogManager.getLogger();

    public List<OrderItem> getOrderItems(Long orderId){
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT orderId, itemId FROM order_items WHERE orderId = ?");) {
            statement.setLong(1, orderId);
            List<OrderItem> orderItems = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orderItems.add(modelFromResultSet(resultSet));
            }
            return orderItems;
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    public OrderItem create(OrderItem orderItem) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO order_items(orderId, itemId) VALUES (?, ?)");) {
            statement.setLong(1, orderItem.getOrderId());
            statement.setLong(2, orderItem.getItem());
            statement.executeUpdate();
            return readLatest();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public OrderItem read(Long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM order_items WHERE orderId = ?");) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery();) {
                resultSet.next();
                return modelFromResultSet(resultSet);
            }
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }


    public OrderItem modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long orderId = resultSet.getLong("orderId");
        Long itemId = resultSet.getLong("itemId");
//        int total = resultSet.getInt("SUM(itemId)");
        OrderItem orderItem = new OrderItem(orderId,itemId);
//        orderItem.setTotal(total);
        return orderItem;
    }

    public int delete(long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM order_items WHERE orderId = ?");) {
            statement.setLong(1, id);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    public int delete(OrderItem orderItem) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM order_items WHERE orderId = ? and itemId = ?");) {
            statement.setLong(1, orderItem.getOrderId());
            statement.setLong(2, orderItem.getItem());
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }


    public OrderItem readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM order_items ORDER BY orderId DESC LIMIT 1");) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
            System.out.println("");
        }
        return null;
    }

}
