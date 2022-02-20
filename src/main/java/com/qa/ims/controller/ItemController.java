package com.qa.ims.controller;
import com.qa.ims.persistence.dao.ItemDAO;
import com.qa.ims.persistence.domain.Item;
import com.qa.ims.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class ItemController implements CrudController<Item>{

    public static final Logger LOGGER = LogManager.getLogger();

    private ItemDAO itemDAO;
    private Utils utils;

    public ItemController(ItemDAO itemDAO, Utils utils) {
        super();
        this.itemDAO = itemDAO;
        this.utils = utils;
    }

    @Override
    public List<Item> readAll() {
        List<Item> items = itemDAO.readAll();
        for (Item item : items) {
            LOGGER.info(item);
        }
        return items;
    }

    @Override
    public Item create() {
        LOGGER.info("Please enter Item Name");
        String itemName = utils.getString();
        LOGGER.info("Please enter Item Quantity");
        Long itemQuantity = utils.getLong();
        LOGGER.info("Please enter Item Price");
        Long itemPrice = utils.getLong();
        Item item = itemDAO.create(new Item(itemName, itemQuantity, itemPrice));
        LOGGER.info("Item Created");
        return item;
    }

    @Override
    public Item update() {
        LOGGER.info("Please enter the id of the item you would like to update");
        Long itemId = utils.getLong();
        LOGGER.info("Please enter the new item Name");
        String itemName = utils.getString();
        LOGGER.info("Please enter the new item quantity available");
        Long itemQuantity = utils.getLong();
        LOGGER.info("Please enter the new item Price");
        Long itemPrice = utils.getLong();
        Item item = itemDAO.update(new Item(itemId, itemName, itemQuantity, itemPrice));
        LOGGER.info("Item Updated");
        return item;
    }

    @Override
    public int delete() {
        LOGGER.info("Please enter the id of the item you would like to delete");
        Long id = utils.getLong();
        return itemDAO.delete(id);
    }
}
