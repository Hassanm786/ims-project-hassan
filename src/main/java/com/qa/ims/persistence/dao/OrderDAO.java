package com.qa.ims.persistence.dao;

import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.DBUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements Dao<Order>{
    public static final Logger LOGGER = LogManager.getLogger();
    private OrderItemDAO orderItemDAO = new OrderItemDAO();
    private ItemDAO itemDAO = new ItemDAO();

    @Override
    public List<Order> readAll() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");) {
            List<Order> orders = new ArrayList<>();
            while (resultSet.next()) {
                orders.add(modelFromResultSet(resultSet));
            }
            return orders;
        } catch (SQLException e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return new ArrayList<>();
    }

    @Override
    public Order read(Long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders WHERE id = ?");) {
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

    @Override
    public Order create(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("INSERT INTO orders(customerId) VALUES (?)");) {
            statement.setLong(1, order.getCustomerId());
            statement.executeUpdate();
            Order leatestOrder = readLatest();
            List<OrderItem> orderItems = order.getOrderItemList();
            for(int i=0; i<orderItems.size();i++) {
                orderItemDAO.create(new OrderItem(leatestOrder.getId(), orderItems.get(i).getItem()));
            }
            return leatestOrder;
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }

        return null;
    }

    @Override
    public Order update(Order order) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection
                     .prepareStatement("UPDATE orders SET customerId = ? WHERE id = ?");) {
            statement.setLong(1, order.getCustomerId());
            statement.executeUpdate();
            return read(order.getId());
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    @Override
    public int delete(long id) {
        try (Connection connection = DBUtils.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE id = ?");) {
            statement.setLong(1, id);
            orderItemDAO.delete(id);
            return statement.executeUpdate();
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return 0;
    }

    @Override
    public Order modelFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Long customerId = resultSet.getLong("customerId");
        return new Order(id, customerId, orderItemDAO.getOrderItems(id));
    }

    public Order readLatest() {
        try (Connection connection = DBUtils.getInstance().getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY id DESC LIMIT 1");) {
            resultSet.next();
            return modelFromResultSet(resultSet);
        } catch (Exception e) {
            LOGGER.debug(e);
            LOGGER.error(e.getMessage());
        }
        return null;
    }

    public Double getOrderCost(Long orderId) {
        Double orderCost = 0.0;
        Order order = read(orderId);
        List<OrderItem> orderItems = order.getOrderItemList();
        for(OrderItem orderItem: orderItems) {
            Item item = itemDAO.read(orderItem.getItem());
            orderCost += item.getItemPrice();
        }
        return orderCost;
    }
}
