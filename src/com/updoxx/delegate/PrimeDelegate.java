package com.updoxx.delegate;

import com.updoxx.domain.ResponseWrapper;
import com.updoxx.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

/**
 * Root delegate class that other Prime Detector implementations can hook into.  Implements provided interface {@link PrimeNumberGenerator}
 */
public abstract class PrimeDelegate implements PrimeNumberGenerator {
    protected int lowerValue;
    protected int higherValue;

    @Override
    public abstract ResponseWrapper generate(int firstValue, int secondValue);

    @Override
    public abstract boolean isPrime(int value);

    protected void orderInputs(int firstValue, int secondValue) {
        if (firstValue < secondValue) {
            lowerValue = firstValue;
            higherValue = secondValue;
        } else {
            lowerValue = secondValue;
            higherValue = firstValue;
        }
    }

    /**
     * Validation method for generate().  Will check and confirm that both inputs are non-negative, and that the maximum value is not INTEGER.MAX_VALUE.
     * Can't use INTEGER.MAX_VALUE, because IntStream size bounds, use INTEGER.MAX_VALUE-1
     *
     * @param response Response that will be returned to {@link #generate(int, int)}, modifies it if any errors are detected.
     */
    protected void validateGenerate(ResponseWrapper response) {
        if (response.getLowerValue() < 0 || response.getHigherValue() < 0) {
            response.setResponseCode(Constants.ERROR);
            String message = Constants.ERR_NEGNUMBER.replaceFirst("@VAL", Integer.toString(response.getLowerValue())).replaceFirst("@VAL", Integer.toString(response.getHigherValue()));
            response.setResponseMessage(message);
        }
        if (response.getLowerValue() == Integer.MAX_VALUE || response.getHigherValue() == Integer.MAX_VALUE) {
            response.setResponseCode(Constants.ERROR);
            String message = Constants.ERR_MAXIMUM;
            response.setResponseMessage(message);
        }
    }

}
