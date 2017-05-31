package com.updoxx.delegate;

import com.updoxx.domain.ResponseWrapper;
import com.updoxx.util.Constants;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Bryan on 5/30/2017.
 */
public class PrimeDelegateTests extends DelegateConstants {
    private PrimeDelegate delegate;

    @Before
    public void setup() {
        this.delegate = new PrimeDelegate();
    }

    @After
    public void tearDown() {
        this.delegate = null;
    }

    @Test
    public void testGenerate_NegativeInput() {
        ResponseWrapper response = delegate.generate(-1, 100);
        assertNotNull("Response Not Null", response);
        assertEquals("Response Is ERROR", response.getResponseCode(), Constants.ERROR);
    }
}
