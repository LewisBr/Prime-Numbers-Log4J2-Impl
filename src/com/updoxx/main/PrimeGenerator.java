package com.updoxx.main;

import com.updoxx.delegate.PrimeInvokerFactory;
import com.updoxx.util.Constants;
import com.updoxx.util.Helper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Bryan Lewis
 * @description Implementation for Updoxx Interview -- Code Challenge.
 * @usage This is a command line based utility that uses user prompts to generate a list of Integers that are primes between two given inputs.
 * Numbers must be alphanumeric and non-negative, and fall between the values of 0 and INTEGER.MAX_VALUE-1 (2,147,483,646).
 * Can be invoked with command line argument of '-h' or '-help' to print manual page before usage (Prints out this blurb).
 * Can be invoked with command line argument of '-d' or '-debug' to print additional debug materials, if you're into that sort of thing.
 * Can be invoked with any number of command line Integer arguments, will pair available command line arguments and print their resultant before moving on.
 * If invoked with an uneven amount of Integer arguments or none at all will prompt user for inputs before completing that specified run.
 * At any available user input if the user inputs 'b' or '-b' the program will attempt to gather the first number again.
 * At any available user input if the user input is just a return character exit the program.
 **/
public class PrimeGenerator {
    private static final Logger LOGGER = LogManager.getLogger(PrimeGenerator.class);

    private static PrimeInvokerFactory factory;

    public static void main(String[] args) {
        LOGGER.info(Constants.INSTRUCTIONS);

        // Instantiate classpath variables
        List<String> arguments;
        String       inputString = null;
        factory = new PrimeInvokerFactory();
        BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));

        // Put array into collection list for easy contains check
        // Note:: In a real program these parameters would be used as system properties passed in on input.  But for ease of use I figured this solution would be fine enough
        arguments = Arrays.asList(args);
        if (arguments.contains("-h") || arguments.contains("-help")) {
            LOGGER.info(Constants.HELP);
        }
        if (arguments.contains("-d") || arguments.contains("-debug")) {
            Helper.setLog4JLogLevel(Level.DEBUG);
        }

        // Process all input arguments, if it's an integer pass it into the invoker factory.
        Iterator<String> argumentIterator = arguments.iterator();
        argumentIterator.forEachRemaining(currentArgument -> {
            LOGGER.debug("Processing Argument: '{}'", currentArgument);
            try {
                int parsedArgument = Integer.parseInt(currentArgument);
                factory.processNewInteger(parsedArgument);
            } catch (NumberFormatException e) {
                // Thought process here, since I'm allowing non-integer values if a value passed in is a non-integer I'll continue until I find one
                LOGGER.debug("Current Argument '{}' is not a valid Integer value -- skipping.", currentArgument);
            }
        });

        // Read in User Input -- If no content is entered exit the loop.
        while (true) {
            try {
                // Print console message of what input the user should input.
                factory.logNextInputState();
                inputString = inputReader.readLine();
                // If input is return character exit the user input section
                if (inputString.length() == 0) {
                    break;
                }
                // Parse integer -- could throw NumberFormatException
                int inputInteger = Integer.parseInt(inputString);
                // Pass new integer to factory.
                factory.processNewInteger(inputInteger);
            } catch (IOException e) {
                LOGGER.error("Unhandled Input, please try another input or hit Enter to exit.");
            } catch (NumberFormatException e) {
                // Add case to allow users to revert back to a fresh state if first input was erroneous.
                if (inputString != null && inputString.toLowerCase().startsWith("b") || inputString.toLowerCase().startsWith("-b")) {
                    LOGGER.info(Constants.INPUT_RESETSTATE, inputString);
                    factory.resetState();
                } else {
                    LOGGER.info(Constants.INPUT_INVALID, inputString);
                }
            }
        }

        // Print exit text
        LOGGER.info(Constants.EXITTEXT);
    }
}
