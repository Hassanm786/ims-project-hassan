package com.qa.ims;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class IMSTest {

    @Test
    public void testConstructor() {
        IMS ims = new IMS();
        assertNotNull(ims);
    }
}
