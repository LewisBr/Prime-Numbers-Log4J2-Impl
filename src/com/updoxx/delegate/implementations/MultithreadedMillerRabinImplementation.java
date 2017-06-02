package com.updoxx.delegate.implementations;

import com.updoxx.delegate.PrimeDelegate;
import com.updoxx.domain.ResponseWrapper;
import com.updoxx.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Comparator.naturalOrder;

/**
 * PrimeDelegate implementation for Millers-Rabin, but added in paralellization in an attempt to optimize the output in certain situations.
 *
 * @documentation https://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test
 */
public class MultithreadedMillerRabinImplementation extends PrimeDelegate {
    private static final Logger             LOGGER      = LogManager.getLogger(MultithreadedMillerRabinImplementation.class);
    private              ArrayList<Integer> primesArray = new ArrayList<>();

    @Override
    public ResponseWrapper generate(int firstNumber, int secondNumber) {
        LOGGER.debug("MillerRabinImplementation#generate called: {} -> {}", firstNumber, secondNumber);

        orderInputs(firstNumber, secondNumber);
        ResponseWrapper ret = new ResponseWrapper(lowerValue, higherValue);

        // Validate that this run would work
        validateGenerate(ret);
        if (Constants.ERROR.equals(ret.getResponseCode())) {
            return ret;
        }

        long startTms = System.currentTimeMillis(); // For calculating run time

        // Prime tester logic - start
        LOGGER.debug("MultithreadedMillerRabinImplementation#generate Starting processing on number range.");
        List syncList = Collections.synchronizedList(new ArrayList<Integer>());
        IntStream.rangeClosed(lowerValue, higherValue).parallel().forEach(i -> {
            if (isPrime(i)) {
                syncList.add(i);
            }
        });
        // Since it's multithreaded we need to sort it so that it guarantees sorting.
        syncList.sort(naturalOrder());
        primesArray.addAll(syncList);

        ret.setListOfPrimes(primesArray);
        LOGGER.debug("MultithreadedMillerRabinImplementation#generate Finished processing on number range.");
        // Prime tester logic - end

        long endTms = System.currentTimeMillis(); // For calculating run time
        ret.setRuntime(endTms - startTms);

        return ret;
    }

    @Override
    public boolean isPrime(int candidate) {
        long d, s;

        if (candidate == 2)
            return true;
        if (candidate < 2)
            return false;

        // until d is odd
        for (d = 0, s = 1; (d & 1) == 0; s++)
            d = (candidate - 1) / fastPow(2, s);

        verification:
        for (long i = 0; i < 100; i++) {
            // random base in the range [2, n-1]
            long base = (long) ((Math.random() * (candidate - 3)) + 2);

            long x = fastPow(base, d, candidate);

            if (x == 1 || x == (candidate - 1))
                continue verification;

            for (long j = 0; j < (s - 1); j++) {
                x = fastPow(x, 2, candidate);
                if (x == 1)
                    return false;
                if (x == (candidate - 1))
                    continue verification;
            }

            return false;
        }

        return true;
    }

    /**
     * Returns the value of the first argument raised to the power of the second
     * argument.
     *
     * @param base     base value for expression
     * @param exponent exponent for base
     * @return the value base^exponent
     */
    private static long fastPow(long base, long exponent) {
        int  shift  = 63; // bit position
        long result = base; // (1 * 1) * base = base

        while (((exponent >> shift--) & 1) == 0) ;

        while (shift >= 0) {
            result = result * result;
            if (((exponent >> shift--) & 1) == 1)
                result = result * base;
        }

        return result;
    }

    /**
     * Returns the value of the first argument raised to the power of the second
     * argument modulo the third argument.
     *
     * @param base     base value for expression
     * @param exponent exponent for base
     * @param modulo   modulus value that enacts on base^exponent
     * @return the value base^exponent % modulo
     */
    private static long fastPow(long base, long exponent, long modulo) {
        int  shift  = 63; // bit position
        long result = base; // (1 * 1) * base = base

        while (((exponent >> shift--) & 1) == 0) ;

        while (shift >= 0) {
            result = (result * result) % modulo;
            if (((exponent >> shift--) & 1) == 1)
                result = (result * base) % modulo;
        }

        return result;
    }
}
