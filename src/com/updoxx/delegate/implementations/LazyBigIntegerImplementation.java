package com.updoxx.delegate.implementations;

import com.updoxx.delegate.PrimeDelegate;
import com.updoxx.domain.ResponseWrapper;
import com.updoxx.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


/**
 * PrimeDelegate implementation for using the simplest approach, BigInteger transitions and using BigInteger#probablePrime.
 * Anticipated this to always be the worst, but figured since I wrote the entire framework mine as well spend 15 minutes implementing
 * a generic, entry level approach.
 */
public class LazyBigIntegerImplementation extends PrimeDelegate {
    private static final Logger        LOGGER      = LogManager.getLogger(LazyBigIntegerImplementation.class);
    private              List<Integer> primesArray = new ArrayList<>();

    @Override
    public ResponseWrapper generate(int firstNumber, int secondNumber) {
        LOGGER.debug("LazyBigIntegerImplementation#generate called: {} -> {}", firstNumber, secondNumber);

        orderInputs(firstNumber, secondNumber);
        ResponseWrapper ret = new ResponseWrapper(lowerValue, higherValue);

        // Validate that this run would work
        validateGenerate(ret);
        if (Constants.ERROR.equals(ret.getResponseCode())) {
            return ret;
        }

        long startTms = System.currentTimeMillis(); // For calculating run time

        // Prime tester logic - start
        LOGGER.debug("LazyBigIntegerImplementation#generate Starting Prime Process.");

        IntStream.rangeClosed(lowerValue, higherValue).forEach(currentNumber -> {
            if (isPrime(currentNumber)) {
                primesArray.add(currentNumber);
            }
        });

        LOGGER.debug("LazyBigIntegerImplementation#generate Ending Prime Process.");
        ret.setListOfPrimes(primesArray);
        // Prime tester logic - end

        long endTms = System.currentTimeMillis(); // For calculating run time
        ret.setRuntime(endTms - startTms);

        return ret;
    }

    @Override
    public boolean isPrime(int n) {
        return BigInteger.valueOf(n).isProbablePrime(50);
    }
}
