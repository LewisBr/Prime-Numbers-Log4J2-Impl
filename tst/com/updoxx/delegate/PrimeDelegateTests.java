package com.updoxx.delegate;

import com.updoxx.domain.ResponseWrapper;
import com.updoxx.util.Constants;
import com.updoxx.util.Helper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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

    /**
     * Validation tests -- Testing Failure Cases.  Success Cases handled in InvokerFactory
     **/
    @Test
    public void testValidate_FailureNegative() {
        ResponseWrapper response = delegate.generate(negativeNumber, secondNumber);
        assertNotNull("Response Not Null", response);
        assertEquals("Response Is ERROR", response.getResponseCode(), Constants.ERROR);
        assertEquals("Response Message Not Negative Number", response.getResponseMessage(), Constants.ERR_NEGNUMBER.replaceFirst("@VAL", Integer.toString(negativeNumber)).replaceFirst("@VAL", Integer
                .toString(secondNumber)));
    }

    /**
     * invokePrimeGenerator tests -- Inheriently tests PrimeDelegate#generate()
     **/
    @Test
    public void invoke_testSuccessZeroes() throws Exception {
        ResponseWrapper response = delegate.generate(0, 0);
        assertNotNull("Response Null", response);
    }

    @Test
    public void invoke_testSuccessForward() throws Exception {
        ResponseWrapper response = delegate.generate(0, 100);
        assertNotNull("Response Null", response);
    }

    @Test
    public void invoke_testSuccessBackwards() throws Exception {
        ResponseWrapper response = delegate.generate(100, 0);
        assertNotNull("Response Null", response);
    }

    @Test
    public void invoke_testSuccessBounds() throws Exception {
        ResponseWrapper response = delegate.generate(0, Integer.MAX_VALUE);
        assertNotNull("Response Null", response);
    }

    @Test
    public void invoke_testSuccessProvidedTest() throws Exception {
        ResponseWrapper response = delegate.generate(7900, 7920);
        assertNotNull("Response Null", response);
    }

    @Test
    public void invoke_testSuccessProvidedTestBackwards() throws Exception {
        ResponseWrapper response = delegate.generate(7920, 7900);
        assertNotNull("Response Null", response);
    }
}
