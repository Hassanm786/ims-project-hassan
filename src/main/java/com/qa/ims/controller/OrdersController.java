package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.OrdersDAO;
import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.Utils;

public class OrdersController implements CrudController<Orders> {
	
	public static final Logger LOGGER = LogManager.getLogger();
	
	private OrdersDAO ordersDAO;
	private Utils utils;
	
	public OrdersController(OrdersDAO ordersDAO, Utils utils) {
		super();
		this.ordersDAO = ordersDAO;
		this.utils = utils;

}
	@Override
	public List<Orders> readAll() {
		List<Orders> orders = ordersDAO.readAll();
		for (Orders order : orders) {
			LOGGER.info(order);
		}
		return orders;
	}

	/**
	 * Creates a customer by taking in user input
	 */
	@Override
	public Orders create() {
		//to make an order you need (this class will combine orders and order-items)
		//-customer id(orders table)
		// go into the dao an insert into the orders table
		//ask if they want to add an item to the order
		//display a list of all the items in the db
		//if yes, ask for itemid (order-items)
		//go into the dao and have a method to insert into the order items table(have another class for order items dao)
		//this would be in awhile loop - so ask again till they say no
		//exit and end so here you say order created
		LOGGER.info("Please enter the customers ID number");
		int customerID = utils.getInt();
		LOGGER.info("Please enter the value of the items");
		Double value = utils.getDouble();
		LOGGER.info("Please add the item");
		String ItemList = utils.getString();
		Orders order = ordersDAO.create(new Orders(customerID, value, ItemList));
		LOGGER.info("Order created");
		return order;
	}

	/**
	 * Updates an existing customer by taking in user input
	 */
	@Override
	public Orders update() {
		LOGGER.info("Please enter the id of the order you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the customer ID");
		int customerID = utils.getInt();
		LOGGER.info("Please enter the new order value");
		Double value = utils.getDouble();
		LOGGER.info("Please delete the items not required");
		String ItemList = utils.getString();
		Orders order = ordersDAO.update(new Orders(id,customerID, value, ItemList));
		LOGGER.info("Order Updated");
		return order;
	}

	/**
	 * Deletes an existing customer by the id of the customer
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long id = utils.getLong();
		return ordersDAO.delete(id);
	}

}
