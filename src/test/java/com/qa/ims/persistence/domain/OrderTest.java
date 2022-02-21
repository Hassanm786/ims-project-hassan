package com.qa.ims.persistence.domain;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OrderTest {
    @Test
    public void testEquals() {
        EqualsVerifier.simple().forClass(Order.class).verify();
    }

    @Test
    public void testGetters() {
        Long orderId = 1L;
        Long customerId = 1L;
        Order order = new Order(orderId,customerId);
        assertEquals(order.getId(),orderId);
        assertEquals(order.getCustomerId(),customerId);
    }

    @Test
    public void testSetters() {
        Long orderId = 1L;
        Long customerId = 1L;
        Order order = new Order(0L, 0L);
        order.setId(orderId);
        order.setCustomerId(customerId);
        assertEquals(order.getId(),orderId);
        assertEquals(order.getCustomerId(),customerId);
    }
}
