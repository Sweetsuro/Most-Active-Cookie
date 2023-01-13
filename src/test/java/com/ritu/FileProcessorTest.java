package com.ritu;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileProcessorTest {
    @Test
    public void testProcessFile() {
        // test.csv DNE
        WrapperFile wf = new WrapperFile("test.csv", "2018-12-08");
        FileProcessor fp = new FileProcessor(wf);
        fp.processFile();
        assertEquals(fp.getMaxCookieNames().size(), 0);

        // invalid file path
        wf = new WrapperFile("cookie_log.csv", "2018-1-08");
        fp = new FileProcessor(wf);
        fp.processFile();
        assertEquals(fp.getMaxCookieNames().size(), 0);

        // date not found in log
        wf = new WrapperFile("cookie_log.csv", "2018-01-08");
        fp = new FileProcessor(wf);
        fp.processFile();
        assertEquals(fp.getMaxCookieNames().size(), 0);

        // valid
        wf = new WrapperFile("cookie_log.csv", "2018-12-08");
        fp = new FileProcessor(wf);
        fp.processFile();
        assertEquals(fp.getMaxCookieNames().size(), 3);
        assertTrue(fp.getMaxCookieNames().contains("fbcn5UAVanZf6UtG"));
        assertTrue(fp.getMaxCookieNames().contains("SAZuXPGUrfbcn5UA"));
        assertTrue(fp.getMaxCookieNames().contains("4sMM2LxV07bPJzwf"));

        // valid
        wf = new WrapperFile("cookie_log.csv", "2018-12-09");
        fp = new FileProcessor(wf);
        fp.processFile();
        assertEquals(fp.getMaxCookieNames().size(), 1);
        assertTrue(fp.getMaxCookieNames().contains("AtY0laUfhglK3lC7"));
    }
}
