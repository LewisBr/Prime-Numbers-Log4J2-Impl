package com.updoxx.delegate;

import com.updoxx.domain.ResponseWrapper;
import com.updoxx.util.Constants;
import com.updoxx.util.Helper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PrimeInvokerFactory {
    private static final Logger LOGGER = LogManager.getLogger(PrimeInvokerFactory.class);
    private PrimeDelegate delegate;
    private int           firstNumber;
    private int           secondNumber;
    private boolean       continuance;

    public PrimeInvokerFactory() {
        this.delegate = new PrimeDelegate();
        this.continuance = false;
    }

    /**
     * Factory class that will take in a new processed integer value, if both first and second numbers are set it will invoke {@link PrimeDelegate#generate(int, int)}.
     *
     * @param newValue New integer value to be processed by the factory.
     */
    public void processNewInteger(int newValue) {
        if (!continuance) {
            firstNumber = newValue;
        } else {
            // Technically don't need this value, but for the sake of legibility I'm keeping it.
            secondNumber = newValue;

            //TODO make it so if second - first > threshold use multithreaded delegate.
            ResponseWrapper response = delegate.generate(firstNumber, secondNumber);

            Helper.processGenerateResponse(response);
        }
        // Reset continuance, this will start a new run or make it so the next number entered triggers the generate task.
        continuance = !continuance;
    }

    public void logNextInputState() {
        if (!continuance) {
            LOGGER.info(Constants.INPUTRONEREADY);
        } else {
            LOGGER.info(Constants.INPUTTWOREADY, firstNumber);
        }
    }
}
