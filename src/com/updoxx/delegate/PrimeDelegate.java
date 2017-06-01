package com.updoxx.delegate;

import com.updoxx.domain.ResponseWrapper;
import com.updoxx.util.Constants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class PrimeDelegate implements PrimeNumberGenerator {
    private static final Logger LOGGER = LogManager.getLogger(PrimeDelegate.class);
    private int lowerValue;
    private int higherValue;

    @Override
    public ResponseWrapper generate(int firstValue, int secondValue) {
        LOGGER.debug("PrimeDelegate#generate called: {} -> {}", firstValue, secondValue);
        orderInputs(firstValue, secondValue);
        ResponseWrapper ret = new ResponseWrapper(lowerValue, higherValue);

        // Validate that this run would work
        validateGenerate(ret);
        if (Constants.ERROR.equals(ret.getResponseCode())) {
            return ret;
        }
        return ret;
    }

    @Override
    public boolean isPrime(int value) {
        return false;
    }

    private void orderInputs(int firstValue, int secondValue) {
        if (firstValue < secondValue) {
            lowerValue = firstValue;
            higherValue = secondValue;
        } else {
            lowerValue = secondValue;
            higherValue = firstValue;
        }
    }

    private void validateGenerate(ResponseWrapper response) {
        if (response.getLowerValue() < 0 || response.getHigherValue() < 0) {
            response.setResponseCode(Constants.ERROR);
            String message = Constants.ERR_NEGNUMBER.replaceFirst("@VAL", Integer.toString(response.getLowerValue())).replaceFirst("@VAL", Integer.toString(response.getHigherValue()));
            response.setResponseMessage(message);
        }
    }
}
