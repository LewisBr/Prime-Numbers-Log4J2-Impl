package com.updoxx.delegate.implementations;

import com.updoxx.delegate.PrimeDelegate;
import com.updoxx.domain.ResponseWrapper;
import com.updoxx.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @note This could be bad for large upper bound implementations.  But as far as I can see there is a requirement of starting with '0'
 * @documentation/source https://neilmadden.wordpress.com/2014/01/30/java-8-sieve-of-eratosthenes/
 */
public class SievePrimeImplementation extends PrimeDelegate {
    private static final Logger        LOGGER      = LogManager.getLogger(SievePrimeImplementation.class);
    private              List<Integer> primesArray = null;

    @Override
    public ResponseWrapper generate(int firstNumber, int secondNumber) {
        LOGGER.debug("SievePrimeImplementation#generate called: {} -> {}", firstNumber, secondNumber);

        orderInputs(firstNumber, secondNumber);
        ResponseWrapper ret = new ResponseWrapper(lowerValue, higherValue);

        // Validate that this run would work
        validateGenerate(ret);
        if (Constants.ERROR.equals(ret.getResponseCode())) {
            return ret;
        }

        long startTms = System.currentTimeMillis(); // For calculating run time

        // Prime tester logic - start
        LOGGER.debug("SievePrimeImplementation#generate Starting Sieve Process.");
        final BitSet sieve = new BitSet(higherValue + 1);
        primesArray = IntStream.rangeClosed(2, higherValue)
                .filter(x -> !sieve.get(x))
                .peek(x -> {
                    if (x * x < higherValue)
                        for (int i = x; i <= higherValue; i += x) {
                            if (i > 0) {
                                sieve.set(i);
                            }
                        }
                })
                .filter(x -> x >= lowerValue)
                .boxed()
                .collect(Collectors.toList());

        ret.setListOfPrimes(primesArray);

        LOGGER.debug("SievePrimeImplementation#generate Ending Sieve Process. Detected {} Primes.", primesArray.size());

        // Prime tester logic - end
        long endTms = System.currentTimeMillis(); // For calculating run time
        ret.setRuntime(endTms - startTms);

        return ret;
    }

    /**
     * isPrime implementation for Sieve.  Given the way Sieve works it just returns if {@link #primesArray} contains the input.
     *
     * @param n Integer to check if it's in the prime set.
     * @return boolean of if given input is Prime or Composite
     */
    @Override
    public boolean isPrime(int n) {
        // Since isPrime() could technically be called without generate() check and make sure n is in the bounds, and that generate() has been run.
        if (primesArray == null || (n > higherValue || n < lowerValue)) {
            generate(n, n);
            return primesArray.contains(n);
        } else {
            return primesArray.contains(n);
        }
    }
}
