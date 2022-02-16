package com.qa.ims.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.dao.ItemsDAO;
import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.Utils;

public class ItemsController implements CrudController<Items> {

	public static final Logger LOGGER = LogManager.getLogger();

	private ItemsDAO itemsDAO;
	private Utils utils;

	public ItemsController(ItemsDAO itemsDAO, Utils utils) {
		super();
		this.itemsDAO = itemsDAO;
		this.utils = utils;
	}

	/**
	 * Reads all items to the logger
	 */
	@Override
	public List<Items> readAll() {
		List<Items> items = itemsDAO.readAll();
		for (Items item : items) {
			LOGGER.info(item);
		}
		return items;
	}

	/**
	 * Creates the item by taking in user input
	 */
	@Override
	public Items create() {
		LOGGER.info("Please enter the item name");
		String Name = utils.getString();
		LOGGER.info("Please enter the value of the item");
		Double Value = utils.getDouble();
		Items items = itemsDAO.create(new Items(Name, Value));
		LOGGER.info("item created");
		return items;
	}

	/**
	 * Updates an existing item by taking in user input
	 */
	@Override
	public Items update() {
		LOGGER.info("Please enter the id of the item you would like to update");
		Long id = utils.getLong();
		LOGGER.info("Please enter the name of the item");
		String Name = utils.getString();
		LOGGER.info("Please enter a new value");
		Double Value = utils.getDouble();
		Items item = itemsDAO.update(new Items(id, Name, Value));
		LOGGER.info("Item Updated");
		return item;
	}

	/**
	 * Deletes an existing item by the id of the item itself
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the item you would like to delete");
		Long id = utils.getLong();
		return itemsDAO.delete(id);
	}

} 


