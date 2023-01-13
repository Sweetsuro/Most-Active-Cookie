package com.ritu;

import static org.junit.Assert.*;

import org.junit.Test;

public class WrapperFileTest {
    @Test
    public void testGetDate() {
        WrapperFile wf = new WrapperFile("test.csv", "2018-12-08");
        assertEquals(wf.getDate(), "2018-12-08");
        assertNotEquals(wf.getDate(), "2018-1-08");
        assertNotEquals(wf.getDate(), "");
        assertNotEquals(wf.getDate(), null);
    }

    @Test
    public void testGetFName() {
        WrapperFile wf = new WrapperFile("test.csv", "2018-12-08");
        assertEquals(wf.getFName(), "test.csv");
        assertNotEquals(wf.getFName(), "test.cs");
        assertNotEquals(wf.getFName(), "");
        assertNotEquals(wf.getFName(), null);
    }

    @Test
    public void testIsValid() {
        WrapperFile wf = new WrapperFile("test.csv", "2018-12-08");
        assertEquals(wf.isValid(), true);
        wf = new WrapperFile("test.cv", "2018-12-08");
        assertEquals(wf.isValid(), false);
        wf = new WrapperFile("test.cv", "2018-12-90");
        assertEquals(wf.isValid(), false);
        wf = new WrapperFile("test.csv", "2018-12-90");
        assertEquals(wf.isValid(), false);
        wf = new WrapperFile("test.csv", null);
        assertEquals(wf.isValid(), false);
        wf = new WrapperFile("test.csv", "");
        assertEquals(wf.isValid(), false);
        wf = new WrapperFile("test.csv", "abs12-s1-ss");
        assertEquals(wf.isValid(), false);
        wf = new WrapperFile("test.csv", "12-02-2002");
        assertEquals(wf.isValid(), false);
    }

    @Test
    public void testValidateDate() {
        WrapperFile wf = new WrapperFile("test.csv", "2018-12-08");
        String date = "2018-12-08";
        assertEquals(wf.validateDate(date), true);
        date = "2022-01-08";
        assertEquals(wf.validateDate(date), true);
        date = "01-08-2002";
        assertEquals(wf.validateDate(date), false);
        date = "2110-14-23";
        assertEquals(wf.validateDate(date), false);
        date = "2s10-14-23";
        assertEquals(wf.validateDate(date), false);
        date = "2110";
        assertEquals(wf.validateDate(date), false);
        date = "";
        assertEquals(wf.validateDate(date), false);
        date = null;
        assertEquals(wf.validateDate(date), false);
    }
}
