package com.updoxx.delegate;

import com.updoxx.util.Helper;
import org.apache.logging.log4j.Level;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InvokerFactoryTests extends DelegateConstants {
    private PrimeInvokerFactory factory;

    @Before
    public void setup() {
        this.factory = new PrimeInvokerFactory();
        Helper.setLog4JLogLevel(Level.ERROR);   // Set log level to ERROR, prevents filling console
    }

    @After
    public void tearDown() {
        this.factory = null;
    }

    /**
     * #processNewInteger tests -- Does not test functionality of generate()
     **/
    // Single Value Tests
    @Test
    public void testGenerate_NegativeInput() {
        factory.processNewInteger(-1);
    }

    @Test
    public void testGenerate_ZeroInput() {
        factory.processNewInteger(0);
    }

    @Test
    public void testGenerate_PositiveInput() {
        factory.processNewInteger(1);
    }

    @Test
    public void testGenerate_MaxIntInput() {
        factory.processNewInteger(Integer.MAX_VALUE);
    }

    @Test
    public void testGenerate_MinIntInput() {
        factory.processNewInteger(Integer.MIN_VALUE);
    }

    // Double Value Tests
    @Test
    public void testGenerate_DoubleNegativeInput() {
        factory.processNewInteger(-1);
        factory.processNewInteger(-1);
    }

    @Test
    public void testGenerate_DoubleZeroInput() {
        factory.processNewInteger(0);
        factory.processNewInteger(0);
    }

    @Test
    public void testGenerate_DoublePositiveInput() {
        factory.processNewInteger(1);
        factory.processNewInteger(1);
    }

    @Test
    public void testGenerate_DoubleMaxIntInput() {
        factory.processNewInteger(Integer.MAX_VALUE);
        factory.processNewInteger(Integer.MAX_VALUE);
    }

    @Test
    public void testGenerate_DoubleMinIntInput() {
        factory.processNewInteger(Integer.MIN_VALUE);
        factory.processNewInteger(Integer.MIN_VALUE);
    }

    // Triple Value Tests
    @Test
    public void testGenerate_TripleNegativeInput() {
        factory.processNewInteger(-1);
        factory.processNewInteger(-1);
        factory.processNewInteger(-1);
    }

    @Test
    public void testGenerate_TripleZeroInput() {
        factory.processNewInteger(0);
        factory.processNewInteger(0);
        factory.processNewInteger(0);
    }

    @Test
    public void testGenerate_TriplePositiveInput() {
        factory.processNewInteger(1);
        factory.processNewInteger(1);
        factory.processNewInteger(1);
    }

    @Test
    public void testGenerate_TripleMaxIntInput() {
        factory.processNewInteger(Integer.MAX_VALUE);
        factory.processNewInteger(Integer.MAX_VALUE);
        factory.processNewInteger(Integer.MAX_VALUE);
    }

    @Test
    public void testGenerate_TripleMinIntInput() {
        factory.processNewInteger(Integer.MIN_VALUE);
        factory.processNewInteger(Integer.MIN_VALUE);
        factory.processNewInteger(Integer.MIN_VALUE);
    }

    // Quadruple Value Tests -- Anything beyond is redundant, 3x and 4x test consecutive runs
    @Test
    public void testGenerate_QuadNegativeInput() {
        factory.processNewInteger(-1);
        factory.processNewInteger(-1);
        factory.processNewInteger(-1);
        factory.processNewInteger(-1);
    }

    @Test
    public void testGenerate_QuadZeroInput() {
        factory.processNewInteger(0);
        factory.processNewInteger(0);
        factory.processNewInteger(0);
        factory.processNewInteger(0);
    }

    @Test
    public void testGenerate_QuadPositiveInput() {
        factory.processNewInteger(1);
        factory.processNewInteger(1);
        factory.processNewInteger(1);
        factory.processNewInteger(1);
    }

    @Test
    public void testGenerate_QuadMaxIntInput() {
        factory.processNewInteger(Integer.MAX_VALUE);
        factory.processNewInteger(Integer.MAX_VALUE);
        factory.processNewInteger(Integer.MAX_VALUE);
        factory.processNewInteger(Integer.MAX_VALUE);
    }

    @Test
    public void testGenerate_QuadMinIntInput() {
        factory.processNewInteger(Integer.MIN_VALUE);
        factory.processNewInteger(Integer.MIN_VALUE);
        factory.processNewInteger(Integer.MIN_VALUE);
        factory.processNewInteger(Integer.MIN_VALUE);
    }

    @Test
    public void testGenerate_QuadRotatingInput() {
        factory.processNewInteger(Integer.MIN_VALUE);
        factory.processNewInteger(12);
        factory.processNewInteger(0);
        factory.processNewInteger(Integer.MAX_VALUE);
    }

}
