package com.updoxx.delegate;

import com.updoxx.delegate.implementations.MillerRabinImplementation;
import com.updoxx.delegate.implementations.MultithreadedMillerRabinImplementation;
import com.updoxx.delegate.implementations.SievePrimeImplementation;
import com.updoxx.domain.ResponseWrapper;
import com.updoxx.util.Constants;
import com.updoxx.util.HelperUtilTests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Testing class that will test various inputs for every defined Implementation of PrimeDelegate.
 * Built in a way that if another implementation were added you could add it to {@link #delegateList}
 */
public class PrimeDelegateTests extends DelegateConstants {
    private SievePrimeImplementation               sieveImpl;
    private MillerRabinImplementation              mrImpl;
    private MultithreadedMillerRabinImplementation mtmrImpl;
    private List<PrimeDelegate>                    delegateList;

    @Before
    public void setup() {
        this.sieveImpl = new SievePrimeImplementation();
        this.mrImpl = new MillerRabinImplementation();
        this.mtmrImpl = new MultithreadedMillerRabinImplementation();
        // Skipping LazyBigIntegerImplementation as it was more for testing bad implementation times, not used at all in the application.
        delegateList = Arrays.asList(sieveImpl, mrImpl, mtmrImpl);
    }

    @After
    public void tearDown() {
        delegateList = null;
        sieveImpl = null;
        mrImpl = null;
        mtmrImpl = null;
    }

    /**
     * Validation tests -- Testing Failure Cases.  Success Cases handled in InvokerFactory
     **/
    @Test
    public void testValidate_FailureNegative() {
        for (PrimeDelegate currentDelegate : delegateList) {
            ResponseWrapper response = currentDelegate.generate(negativeNumber, secondNumber);
            assertNotNull("Response Not Null", response);
            assertEquals("Response Is ERROR", response.getResponseCode(), Constants.ERROR);
            assertEquals("Response Message Not Negative Number", response.getResponseMessage(), Constants.ERR_NEGNUMBER.replaceFirst("@VAL", Integer.toString(negativeNumber)).replaceFirst("@VAL", Integer
                    .toString(secondNumber)));
        }
    }

    @Test
    public void testValidate_FailureMaxNegative() {
        for (PrimeDelegate currentDelegate : delegateList) {
            ResponseWrapper response = currentDelegate.generate(Integer.MIN_VALUE, secondNumber);
            assertNotNull("Response Not Null", response);
            assertEquals("Response Is ERROR", response.getResponseCode(), Constants.ERROR);
            assertEquals("Response Message Not Negative Number", response.getResponseMessage(), Constants.ERR_NEGNUMBER.replaceFirst("@VAL", Integer.toString(Integer.MIN_VALUE)).replaceFirst("@VAL", Integer
                    .toString(secondNumber)));
        }
    }

    @Test
    public void testValidate_FailureMaxValue() throws Exception {
        for (PrimeDelegate currentDelegate : delegateList) {
            ResponseWrapper response = currentDelegate.generate(0, Integer.MAX_VALUE);
            assertNotNull("Response Not Null", response);
            assertEquals("Response Is ERROR", response.getResponseCode(), Constants.ERROR);
            assertEquals("Response Message Not Maximum Value", response.getResponseMessage(), Constants.ERR_MAXIMUM);
        }
    }

    /**
     * invokePrimeGenerator tests -- Inheriently tests PrimeDelegate#generate()
     **/
    @Test
    public void invoke_testSuccessZeroes() throws Exception {
        for (PrimeDelegate currentDelegate : delegateList) {
            ResponseWrapper response = currentDelegate.generate(0, 0);
            assertNotNull("Response Null", response);
            assertEquals("Prime Set Equality", response.getListOfPrimes(), Arrays.asList());
        }
    }

    @Test
    public void invoke_testBelowTwo() throws Exception {
        for (PrimeDelegate currentDelegate : delegateList) {
            ResponseWrapper response = currentDelegate.generate(0, 1);
            assertNotNull("Response Null", response);
            assertEquals("Prime Set Equality", response.getListOfPrimes(), Arrays.asList());
        }
    }

    @Test
    public void invoke_testSuccessForward() throws Exception {
        for (PrimeDelegate currentDelegate : delegateList) {
            ResponseWrapper response = currentDelegate.generate(0, 100);
            assertNotNull("Response Null", response);
            assertEquals("Prime Set Equality", response.getListOfPrimes(), Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97));
        }
    }

    @Test
    public void invoke_testSuccessBackwards() throws Exception {
        for (PrimeDelegate currentDelegate : delegateList) {
            ResponseWrapper response = currentDelegate.generate(100, 0);
            assertNotNull("Response Null", response);
            assertEquals("Prime Set Equality", response.getListOfPrimes(), Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97));
        }
    }

    @Test
    public void invoke_testSuccessExactNumber() throws Exception {
        for (PrimeDelegate currentDelegate : delegateList) {
            ResponseWrapper response = currentDelegate.generate(101, 101);
            assertNotNull("Response Null", response);
            assertEquals("Prime Set Equality", response.getListOfPrimes(), Arrays.asList(101));
        }
    }

    @Test
    public void invoke_testSuccessExactNumberNonStandard() throws Exception {
        for (PrimeDelegate currentDelegate : delegateList) {
            ResponseWrapper response = currentDelegate.generate(2, 2);
            assertNotNull("Response Null", response);
            assertEquals("Prime Set Equality", response.getListOfPrimes(), Arrays.asList(2));
        }
    }

    @Test
    public void invoke_testSuccessExactNumberNotPrime() throws Exception {
        for (PrimeDelegate currentDelegate : delegateList) {
            ResponseWrapper response = currentDelegate.generate(100, 100);
            assertNotNull("Response Null", response);
            assertEquals("Prime Set Equality", response.getListOfPrimes(), Arrays.asList());
        }
    }

    @Test
    public void invoke_testSuccessProvidedTest() throws Exception {
        for (PrimeDelegate currentDelegate : delegateList) {
            ResponseWrapper response = currentDelegate.generate(7900, 7920);
            assertNotNull("Response Null", response);
            assertEquals("Prime Set Equality", response.getListOfPrimes(), Arrays.asList(7901, 7907, 7919));
        }
    }

    @Test
    public void invoke_testSuccessProvidedTestBackwards() throws Exception {
        for (PrimeDelegate currentDelegate : delegateList) {
            ResponseWrapper response = currentDelegate.generate(7920, 7900);
            assertNotNull("Response Null", response);
            assertEquals("Prime Set Equality", response.getListOfPrimes(), Arrays.asList(7901, 7907, 7919));
        }
    }

    @Test
    public void invoke_testSuccessProvidedFirst26() throws Exception {
        for (PrimeDelegate currentDelegate : delegateList) {
            ResponseWrapper response = currentDelegate.generate(0, 101);
            assertNotNull("Response Null", response);
            assertEquals("Count of Primes", response.getListOfPrimes().size(), 26);
            assertEquals("Prime Set Equality", response.getListOfPrimes(), Arrays.asList(2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101));
        }
    }

    // Disabled due to the amount of time it takes to complete.  But just wanted to show I thought of it.
    public void invoke_testSuccessBounds() throws Exception {
        for (PrimeDelegate currentDelegate : delegateList) {
            ResponseWrapper response = currentDelegate.generate(0, Integer.MAX_VALUE - 1);
            Logger          LOGGER   = LogManager.getLogger(HelperUtilTests.class);
            LOGGER.info(response.getRuntime());
            LOGGER.info(response.getListOfPrimes().size());
            assertNotNull("Response Null", response);
            assertEquals("Count of Primes", 105097565, response.getListOfPrimes().size());
        }
    }
}
