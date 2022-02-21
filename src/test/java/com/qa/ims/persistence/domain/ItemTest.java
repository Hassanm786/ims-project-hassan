package com.qa.ims.persistence.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTest {
    @Test
    public void testEquals() {
        EqualsVerifier.simple().forClass(Item.class).verify();
    }

    @Test
    public void testGetters() {
        Long itemId = 1L;
        String itemName = "Banana";
        Long itemQuantity = 5L;
        Long itemPrice = 10L;
        Item item = new Item(itemId,itemName,itemQuantity, itemPrice);
        assertEquals(item.getId(),itemId);
        assertEquals(item.getItemName(),itemName);
        assertEquals(item.getItemQuantity(),itemQuantity);
        assertEquals(item.getItemPrice(),itemPrice);
    }

    @Test
    public void testSetters() {
        Long itemId = 1L;
        String itemName = "Banana";
        Long itemQuantity = 5L;
        Long itemPrice = 10L;
        Item item = new Item(null,null,null, null);
        item.setId(itemId);
        item.setItemName(itemName);
        item.setItemQuantity(itemQuantity);
        item.setItemPrice(itemPrice);
        assertEquals(item.getId(),itemId);
        assertEquals(item.getItemName(),itemName);
        assertEquals(item.getItemQuantity(),itemQuantity);
        assertEquals(item.getItemPrice(),itemPrice);
    }

    @Test
    public void testThreeArgConstructor() {
        String itemName = "Banana";
        Long itemQuantity = 5L;
        Long itemPrice = 10L;
        Item item = new Item(itemName,itemQuantity, itemPrice);
        assertEquals(item.getItemName(),itemName);
        assertEquals(item.getItemQuantity(),itemQuantity);
        assertEquals(item.getItemPrice(),itemPrice);
    }
}
