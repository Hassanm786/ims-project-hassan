package com.qa.ims.persistence.domain;

import org.junit.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

import static org.junit.Assert.assertEquals;


public class CustomerTest {

	@Test
	public void testEquals() {
		EqualsVerifier.simple().forClass(Customer.class).verify();
	}

	@Test
	public void testGetters() {
		String firstName = "Allen";
		String surname = "Walker";
		Customer customer = new Customer(firstName,surname);
		assertEquals(customer.getFirstName(), firstName);
		assertEquals(customer.getSurname(), surname);
	}

	@Test
	public void testSetters() {
		String firstName = "Allen";
		String surname = "Walker";
		Customer customer = new Customer(null,null);
		customer.setFirstName(firstName);
		customer.setSurname(surname);
		assertEquals(customer.getFirstName(), firstName);
		assertEquals(customer.getSurname(), surname);
	}

}
