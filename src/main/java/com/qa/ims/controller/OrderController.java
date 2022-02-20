package com.qa.ims.controller;

import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.dao.OrderDAO;
import com.qa.ims.persistence.dao.OrderItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.persistence.domain.Order;
import com.qa.ims.persistence.domain.OrderItem;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class OrderController implements CrudController<Order>{
    public static final Logger LOGGER = LogManager.getLogger();

    private OrderDAO orderDAO;
    private Utils utils;
    private ItemDAO itemDAO;
    private OrderItemDAO orderItemDAO = new OrderItemDAO();

    public OrderController(OrderDAO orderDAO, Utils utils, ItemDAO itemDAO) {
        super();
        this.orderDAO = orderDAO;
        this.utils = utils;
        this.itemDAO = itemDAO;
    }

    @Override
    public List<Order> readAll() {
        List<Order> orders = orderDAO.readAll();
        for (Order order : orders) {
            List<OrderItem> orderItems = orderItemDAO.getOrderItems(order.getId());
            order.setOrderItemList(orderItems);
            LOGGER.info(order);
        }
        return orders;
    }

    @Override
    public Order create() {
        LOGGER.info("Please enter Customer Id");
        Long customerId = utils.getLong();
        List<OrderItem> listOfOrderItems = new ArrayList<>();
        OrderItem orderItem = null;
        String proceed = "N";
        do {
            LOGGER.info("Please enter Item Id's you want to add to the order");
            orderItem = new OrderItem(customerId, utils.getLong());
            Item item = itemDAO.read(orderItem.getItem());
            if(item == null) {
                LOGGER.info("Item doesn't exist in database, Please add a valid Id");
                continue;
            } else  {
                listOfOrderItems.add(orderItem);
            }
            LOGGER.info("Do you want to add more Items? (Y/N)");
            proceed = utils.getString();
        }while(proceed.equalsIgnoreCase("Yes") || proceed.equalsIgnoreCase("Y"));
        Order order = orderDAO.create(new Order(customerId, listOfOrderItems));
        LOGGER.info("Order created");
        return order;
    }

    @Override
    public Order update() {
        LOGGER.info("ADD: Add an item to an Order");
        LOGGER.info("VIEW: View Order details ");
        LOGGER.info("CALCULATE: Calculate total cost for an Order");
        LOGGER.info("DELETE: Delete an item from Order an order");
        String menu = utils.getString();
        if (menu.equalsIgnoreCase("ADD")) {
            LOGGER.info("Please enter the Order Id in which you want to add an item");
            Long orderId = utils.getLong();
            LOGGER.info("Enter the item Id's you wish to add ");
            Long itemId = utils.getLong();
            orderItemDAO.create(new OrderItem(orderId, itemId));
            LOGGER.info("Order updated!");
        } else if(menu.equalsIgnoreCase("VIEW")) {
            LOGGER.info("Please enter the Id of the order to wish to view");
            Long orderId = utils.getLong();
            List<OrderItem> orderItems = orderItemDAO.getOrderItems(orderId);
            for(OrderItem orderItem: orderItems) {
                LOGGER.info(orderItem);
            }
        }else if(menu.equalsIgnoreCase("CALCULATE")) {
            LOGGER.info("Please enter the id of the order, to see the total cost of the order");
            Long orderId = utils.getLong();
            Double price = orderDAO.getOrderCost(orderId);
            LOGGER.info("Price: "+price);
        } else if(menu.equalsIgnoreCase("DELETE")) {
            LOGGER.info("Please enter the id of the order that you want to delete an item from");
            Long orderId = utils.getLong();
            LOGGER.info("Enter the item Id you want to Delete");
            Long itemId = utils.getLong();
            orderItemDAO.delete(new OrderItem(orderId, itemId));
            LOGGER.info("Order updated!");
        } else {
            LOGGER.info("Invalid Operation");
            update();
        }
        return null;
    }

    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the order you would like to delete");
        Long id = utils.getLong();
        return orderDAO.delete(id);
    }

}
