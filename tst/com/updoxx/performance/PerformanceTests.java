package com.updoxx.performance;

import com.updoxx.delegate.implementations.LazyBigIntegerImplementation;
import com.updoxx.delegate.implementations.MillerRabinImplementation;
import com.updoxx.delegate.implementations.MultithreadedMillerRabinImplementation;
import com.updoxx.delegate.implementations.SievePrimeImplementation;
import com.updoxx.domain.ResponseWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This testing class is used to test various spot performance differences between various implementations of PrimeGenerator.
 *
 * @note Given more time and if this were more of a real project I would use some benchmarking libraries.  But I just wanted to play with timings
 * and gauge performance statistics on what I implemented.
 */
public class PerformanceTests extends PerformanceConstants {
    private Logger                                 LOGGER;
    private SievePrimeImplementation               sieveImpl;
    private LazyBigIntegerImplementation           lbiImpl;
    private MillerRabinImplementation              mrImpl;
    private MultithreadedMillerRabinImplementation mtmrImpl;

    @Before
    public void setup() {
        LOGGER = LogManager.getLogger(PerformanceTests.class);
        sieveImpl = new SievePrimeImplementation();
        lbiImpl = new LazyBigIntegerImplementation();
        mrImpl = new MillerRabinImplementation();
        mtmrImpl = new MultithreadedMillerRabinImplementation();
    }

    @After
    public void tearDown() {
    }

    //////////
    // CASE #0: Fun Times -- Does LazyBigInt Implementation ever give benefit?
    // Reason: Since I wrote the factory I added the easiest solution really fast and figured I'd see how it would stack up against better solutions

    // Technically performs the same as non-threaded MillerRabin with [0,2]
    @Test
    public void perf_badBaseCase() throws Exception {
        int             firstNum  = 0;
        int             secondNum = 2;
        ResponseWrapper resp1     = sieveImpl.generate(firstNum, secondNum);
        ResponseWrapper resp2     = lbiImpl.generate(firstNum, secondNum);
        ResponseWrapper resp3     = mrImpl.generate(firstNum, secondNum);
        ResponseWrapper resp4     = mtmrImpl.generate(firstNum, secondNum);
        assertEquals(resp1.getListOfPrimes(), resp4.getListOfPrimes());
        LOGGER.info("A: {} - {}", resp1.getRuntime(), resp1.getListOfPrimes().size());
        LOGGER.info("B: {} - {}", resp2.getRuntime(), resp2.getListOfPrimes().size());
        LOGGER.info("C: {} - {}", resp3.getRuntime(), resp3.getListOfPrimes().size());
        LOGGER.info("D: {} - {}", resp4.getRuntime(), resp4.getListOfPrimes().size());
    }

    // Performs worse than Millers Rabin (both), but still better than Sieve
    @Test
    public void perf_badSmall() throws Exception {
        int             firstNum  = 0;
        int             secondNum = 100;
        ResponseWrapper resp1     = sieveImpl.generate(firstNum, secondNum);
        ResponseWrapper resp2     = lbiImpl.generate(firstNum, secondNum);
        ResponseWrapper resp3     = mrImpl.generate(firstNum, secondNum);
        ResponseWrapper resp4     = mtmrImpl.generate(firstNum, secondNum);
        assertEquals(resp1.getListOfPrimes(), resp4.getListOfPrimes());
        LOGGER.info("A: {} - {}", resp1.getRuntime(), resp1.getListOfPrimes().size());
        LOGGER.info("B: {} - {}", resp2.getRuntime(), resp2.getListOfPrimes().size());
        LOGGER.info("C: {} - {}", resp3.getRuntime(), resp3.getListOfPrimes().size());
        LOGGER.info("D: {} - {}", resp4.getRuntime(), resp4.getListOfPrimes().size());
    }

    // As expected, performs worst around this point
    @Test
    public void perf_badMid() throws Exception {
        int             firstNum  = 0;
        int             secondNum = 10000;
        ResponseWrapper resp1     = sieveImpl.generate(firstNum, secondNum);
        ResponseWrapper resp2     = lbiImpl.generate(firstNum, secondNum);
        ResponseWrapper resp3     = mrImpl.generate(firstNum, secondNum);
        ResponseWrapper resp4     = mtmrImpl.generate(firstNum, secondNum);
        assertEquals(resp1.getListOfPrimes(), resp4.getListOfPrimes());
        LOGGER.info("A: {} - {}", resp1.getRuntime(), resp1.getListOfPrimes().size());
        LOGGER.info("B: {} - {}", resp2.getRuntime(), resp2.getListOfPrimes().size());
        LOGGER.info("C: {} - {}", resp3.getRuntime(), resp3.getListOfPrimes().size());
        LOGGER.info("D: {} - {}", resp4.getRuntime(), resp4.getListOfPrimes().size());
    }

    // Still worst, but noticeable gain on Sieve.
    @Test
    public void perf_badMidOffset() throws Exception {
        int             firstNum  = 10000;
        int             secondNum = 20000;
        ResponseWrapper resp1     = sieveImpl.generate(firstNum, secondNum);
        ResponseWrapper resp2     = lbiImpl.generate(firstNum, secondNum);
        ResponseWrapper resp3     = mrImpl.generate(firstNum, secondNum);
        ResponseWrapper resp4     = mtmrImpl.generate(firstNum, secondNum);
        assertEquals(resp1.getListOfPrimes(), resp4.getListOfPrimes());
        LOGGER.info("A: {} - {}", resp1.getRuntime(), resp1.getListOfPrimes().size());
        LOGGER.info("B: {} - {}", resp2.getRuntime(), resp2.getListOfPrimes().size());
        LOGGER.info("C: {} - {}", resp3.getRuntime(), resp3.getListOfPrimes().size());
        LOGGER.info("D: {} - {}", resp4.getRuntime(), resp4.getListOfPrimes().size());
    }

    //////////
    // CASE #1: Sieve vs MillersRabin (Non threaded)
    // Reason: Since Sieve builds an entire array of objects but we might only need a subset,
    // at what point does the overhead of maintaining a dead list outweigh the individual computation of Millers Rabin

    // Mental notes on how performance charts should look:
    // Sieve performs better at large ranges, but performs gradually worse as the top end number is larger
    // MillersRabin performs better at any number range, but performs incrementally worse the large the range between [low,high] is.

    // At low ends performs better, most likely because of overhead on Sieve
    @Test
    public void perf_testC1BaseCase() throws Exception {
        int             firstNum  = 0;
        int             secondNum = 2;
        ResponseWrapper resp1     = sieveImpl.generate(firstNum, secondNum);
        ResponseWrapper resp2     = mrImpl.generate(firstNum, secondNum);
        LOGGER.info("A: {} - {}", resp1.getRuntime(), resp1.getListOfPrimes().size());
        LOGGER.info("B: {} - {}", resp2.getRuntime(), resp2.getListOfPrimes().size());
    }

    // Performs better at low volumes still
    @Test
    public void perf_testC1Low() throws Exception {
        int             firstNum  = 0;
        int             secondNum = 100;
        ResponseWrapper resp1     = sieveImpl.generate(firstNum, secondNum);
        ResponseWrapper resp2     = mrImpl.generate(firstNum, secondNum);
        LOGGER.info("A: {} - {}", resp1.getRuntime(), resp1.getListOfPrimes().size());
        LOGGER.info("B: {} - {}", resp2.getRuntime(), resp2.getListOfPrimes().size());
    }

    // Tinkered with numbers, looks like ~10000 records in is the tipping point with [0,10000]
    @Test
    public void perf_testC1LowTippingPoint() throws Exception {
        int             firstNum  = 0;
        int             secondNum = 10000;
        ResponseWrapper resp1     = sieveImpl.generate(firstNum, secondNum);
        ResponseWrapper resp2     = mrImpl.generate(firstNum, secondNum);
        LOGGER.info("A: {} - {}", resp1.getRuntime(), resp1.getListOfPrimes().size());
        LOGGER.info("B: {} - {}", resp2.getRuntime(), resp2.getListOfPrimes().size());
    }

    // And it appears that with an offset they match around [15000,25000].
    // So thinking rule will be on low end -- Rabin for High<=25000 && diff(low,high)<10000
    @Test
    public void perf_testC1LowTippingPointOffset() throws Exception {
        int             firstNum  = 15000;
        int             secondNum = 25000;
        ResponseWrapper resp1     = sieveImpl.generate(firstNum, secondNum);
        ResponseWrapper resp2     = mrImpl.generate(firstNum, secondNum);
        LOGGER.info("A: {} - {}", resp1.getRuntime(), resp1.getListOfPrimes().size());
        LOGGER.info("B: {} - {}", resp2.getRuntime(), resp2.getListOfPrimes().size());
    }

    // And just a test to show that at some point Sieve performs better than Rabin at low levels
    @Test
    public void perf_testC1SieveOutperform() throws Exception {
        int             firstNum  = 24800000;
        int             secondNum = 25000000;
        ResponseWrapper resp1     = sieveImpl.generate(firstNum, secondNum);
        ResponseWrapper resp2     = mrImpl.generate(firstNum, secondNum);
        LOGGER.info("RATIO:: {}", (double) (secondNum - firstNum) / firstNum);
        LOGGER.info("A: {} - {}", resp1.getRuntime(), resp1.getListOfPrimes().size());
        LOGGER.info("B: {} - {}", resp2.getRuntime(), resp2.getListOfPrimes().size());
    }

    //////////
    // CASE #2: MillersRabin vs Multithreaded MillersRabin
    // Reason: Since from {CASE #1} we know that MillersRabin performs better, then worse, then better again we can use this
    // to make a more optimal solution for the larger top end values.  Basically trying to figure out at what point does
    // the overhead of creating, managing threads and sorting the list more efficient than single thread computation

    // Note: After testing this, pleasant surprise was lambda.parallel didn't really add as much overhead as anticipated.
    // So I think that multithreading is always the approach to use when diff(low,high) > 250 or so.

    // Mental notes on how performance charts should look:
    // Since Millers-Rabin calculates on each element individually both will have worse performance as diff(low,high) goes up.

    @Test
    public void perf_testC2BaseCase() throws Exception {
        int             firstNum  = 0;
        int             secondNum = 2;
        ResponseWrapper resp1     = mrImpl.generate(firstNum, secondNum);
        LOGGER.info("A: {} - {}", resp1.getRuntime(), resp1.getListOfPrimes().size());
        ResponseWrapper resp2 = mtmrImpl.generate(firstNum, secondNum);
        LOGGER.info("B: {} - {}", resp2.getRuntime(), resp2.getListOfPrimes().size());
    }

    @Test
    public void perf_testC2MidRange() throws Exception {
        int             firstNum  = 0;
        int             secondNum = 100000;
        ResponseWrapper resp1     = mrImpl.generate(firstNum, secondNum);
        LOGGER.info("A: {} - {}", resp1.getRuntime(), resp1.getListOfPrimes().size());
        ResponseWrapper resp2 = mtmrImpl.generate(firstNum, secondNum);
        LOGGER.info("B: {} - {}", resp2.getRuntime(), resp2.getListOfPrimes().size());
    }


    // These tests take a long time, disabling. Manually use if testing large end efficiency.
    public void disabled_perf_testC2HighRange() throws Exception {
        int             firstNum  = 0;
        int             secondNum = 10000000;
        ResponseWrapper resp1     = mrImpl.generate(firstNum, secondNum);
        LOGGER.info("A: {} - {}", resp1.getRuntime(), resp1.getListOfPrimes().size());
        ResponseWrapper resp2 = mtmrImpl.generate(firstNum, secondNum);
        LOGGER.info("B: {} - {}", resp2.getRuntime(), resp2.getListOfPrimes().size());
    }

    public void disabled_perf_testC2VeryHighRange() throws Exception {
        int             firstNum  = 0;
        int             secondNum = Integer.MAX_VALUE / 10;
        ResponseWrapper resp1     = mrImpl.generate(firstNum, secondNum);
        LOGGER.info("A: {} - {}", resp1.getRuntime(), resp1.getListOfPrimes().size());
        ResponseWrapper resp2 = mtmrImpl.generate(firstNum, secondNum);
        LOGGER.info("B: {} - {}", resp2.getRuntime(), resp2.getListOfPrimes().size());
    }
}
