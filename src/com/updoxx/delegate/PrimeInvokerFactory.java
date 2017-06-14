package com.updoxx.delegate;

import com.updoxx.delegate.implementations.MillerRabinImplementation;
import com.updoxx.delegate.implementations.MultithreadedMillerRabinImplementation;
import com.updoxx.delegate.implementations.SievePrimeImplementation;
import com.updoxx.domain.ResponseWrapper;
import com.updoxx.util.Constants;
import com.updoxx.util.Helper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Factory class that will manage when and how to call {@link PrimeDelegate#generate(int, int)}.  Variably calls which implementation depending on
 * factors for the input.
 */
public class PrimeInvokerFactory {
    private static final Logger LOGGER = LogManager.getLogger(PrimeInvokerFactory.class);
    private PrimeDelegate delegate;
    private int           firstNumber;
    private int           secondNumber;
    private boolean       continuance;

    public PrimeInvokerFactory() {
        this.continuance = false;
    }

    /**
     * Factory class that will take in a new processed integer value, if both first and second numbers are set it will invoke {@link PrimeDelegate#generate(int, int)}.
     *
     * @param newValue New integer value to be processed by the factory.
     */
    public void processNewInteger(int newValue) {
        // If this should be the first number, just set it
        if (!continuance) {
            firstNumber = newValue;
        } else {
            ResponseWrapper response;
            // Technically don't need this value, but for the sake of legibility I'm keeping it.
            secondNumber = newValue;

            // Get some statistics on the run for determining which implementation to use.
            double diffRatio = firstNumber > secondNumber ? (double) (firstNumber - secondNumber) / firstNumber : (double) (secondNumber - firstNumber) / secondNumber;
            int    diffRange = secondNumber - firstNumber;

            // Invoke the correct generate() implementation based on the run criteria.
            // Use Miller-Rabin when high bound is a low number, when it's very large but not doing a majority of the range, and when it's exceedingly high.
            // Staged to various cases.  If this were a real program would be more beneficial to use a benchmarking utility to find a direct correlation instead of case analysis.
            if (secondNumber <= 100 ||
                    (secondNumber >= Integer.MAX_VALUE / 100 && diffRatio <= .10) ||
                    (secondNumber >= Integer.MAX_VALUE / 10 && diffRatio <= .3) ||
                    (secondNumber >= Integer.MAX_VALUE / 6 && diffRatio <= .7)) {
                // If we are only computing ~250 numbers do it non-threaded, otherwise thread it. This is because of the base overhead time from thread management.
                if (diffRange <= 250) {
                    delegate = new MillerRabinImplementation();
                } else {
                    delegate = new MultithreadedMillerRabinImplementation();
                }
            } else {
                // Use Sieve from above low, but below exceedingly high numbers.
                delegate = new SievePrimeImplementation();
            }

            // Invoke delegate to get list of primes for the run.
            response = delegate.generate(firstNumber, secondNumber);

            // Log the appropriate message based on the response.
            Helper.processGenerateResponse(response);
        }
        // Toggle continuance, this will start a new run or make it so the next number entered triggers the generate task.
        continuance = !continuance;
    }

    /**
     * Helper method that will prompt the user for input according to what the state of the factory is.  Either asking for the first or second input.
     */
    public void logNextInputState() {
        if (!continuance) {
            LOGGER.info(Constants.INPUT_ONEREADY);
        } else {
            LOGGER.info(Constants.INPUT_TWOREADY, firstNumber);
        }
    }

    /**
     * Helper method that will reset {@link #continuance} to false, making it so the first value can be inputted again without calling generate().
     */
    public void resetState() {
        this.continuance = false;
    }
}
