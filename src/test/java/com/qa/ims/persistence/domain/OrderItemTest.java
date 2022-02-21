package com.qa.ims.persistence.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderItemTest {
    @Test
    public void testEquals() {
        EqualsVerifier.simple().forClass(OrderItem.class).verify();
    }

    @Test
    public void testGetters() {
        Long orderId = 1L;
        Long itemId = 1L;
        OrderItem orderItem = new OrderItem(orderId,itemId);
        assertEquals(orderItem.getOrderId(),orderId);
        assertEquals(orderItem.getItem(),itemId);
    }

    @Test
    public void testSetters() {
        Long orderId = 1L;
        Long itemId = 1L;
        OrderItem orderItem = new OrderItem(0L,0L);
        orderItem.setOrderId(orderId);
        orderItem.setItem(itemId);
        assertEquals(orderItem.getOrderId(),orderId);
        assertEquals(orderItem.getItem(),itemId);
    }
}

