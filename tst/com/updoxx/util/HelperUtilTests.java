package com.updoxx.util;

import com.updoxx.delegate.PrimeDelegate;
import com.updoxx.delegate.implementations.SievePrimeImplementation;
import com.updoxx.domain.ResponseWrapper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class HelperUtilTests extends UtilConstants {
    private Logger        LOGGER;
    private PrimeDelegate delegate;

    @Before
    public void setup() {
        LOGGER = LogManager.getLogger(HelperUtilTests.class);
        Helper.setLog4JLogLevel(Level.ERROR);   // Set logging level to ERROR to prevent filling console
        delegate = new SievePrimeImplementation();
    }

    @After
    public void tearDown() {
        Helper.setLog4JLogLevel(Level.INFO);   // Reset log levels each time to INFO, if running suite breaks some tests erroneously.
    }

    /**
     * Log4J2 tests
     **/
    // Default Test -- Logging level set to INFO by Default in config file.
    // Disabled due to race condition on check with setLog4JLogLevel
    public void disabled_log4j_testDefaultLevel() throws Exception {
        assertEquals("Default Level is INFO", LOGGER.getLevel(), Level.INFO);
    }

    // Disabled due to three reasons, [1] Race condition | [2] Console clutter | [3] Technically tested by other ones since in setup() log is changed to ERROR
    public void disabled_og4j_testSetLogLevel() throws Exception {
        assertEquals("Default Level is ERROR", LOGGER.getLevel(), Level.ERROR);
        Helper.setLog4JLogLevel(Level.DEBUG);
        assertEquals("Default Level is DEBUG", LOGGER.getLevel(), Level.DEBUG);
    }

    // Tests for Helper#setLog4JLogLevel
    @Test
    public void log4j_testNull() throws Exception {
        Helper.setLog4JLogLevel(null);
        assertEquals("Default Level is ERROR", LOGGER.getLevel(), Level.ERROR);
    }

    /**
     * processGenerateResponse tests
     **/
    // Edge case test
    @Test
    public void wrapper_testNull() throws Exception {
        try {
            Helper.processGenerateResponse(null);
        } catch (Exception e) {
            fail("Should never have reached here.");
        }
    }

    // Success Tests
    @Test
    public void wrapper_testSuccess() throws Exception {
        try {
            Helper.setLog4JLogLevel(Level.WARN);   // Set level to Error to avoid filling console
            ResponseWrapper wrapper = new ResponseWrapper(new ArrayList<>(), Constants.SUCCESS, null, 0, 0);
            Helper.processGenerateResponse(wrapper);
        } catch (Exception e) {
            fail("Should never have reached here.");
        }
    }

    @Test
    // Note this test inherently prints to the console.
    public void wrapper_testSuccessDebug() throws Exception {
        try {
            Helper.setLog4JLogLevel(Level.DEBUG);   // Set level to debug
            ResponseWrapper wrapper = new ResponseWrapper(new ArrayList<>(), Constants.SUCCESS, null, 0, 0);
            Helper.processGenerateResponse(wrapper);
        } catch (Exception e) {
            fail("Should never have reached here.");
        }
    }

    @Test
    public void wrapper_testError() throws Exception {
        try {
            Helper.setLog4JLogLevel(Level.WARN);   // Set level to Error to avoid filling console
            ResponseWrapper wrapper = new ResponseWrapper(new ArrayList<>(), Constants.ERROR, "JUnit Generic Error", 0, 0);
            Helper.processGenerateResponse(wrapper);
        } catch (Exception e) {
            fail("Should never have reached here.");
        }
    }

}
