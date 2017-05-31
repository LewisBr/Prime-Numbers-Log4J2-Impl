package com.updoxx.main;

import com.updoxx.util.Helper;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * @author Bryan Lewis
 * @description Implementation for Updoxx Interview -- Code Challenge.
 * @usage This is a command line based utility that uses user prompts to generate a list of Integers that are primes between two given inputs.
 * Numbers must be alphanumeric and non-negative, and fall between the values of 0 and INTEGER.MAX_VALUE (2,147,483,647).
 * Can be invoked with command line argument of -h or -help to print manual page before usage (Prints out this blurb).
 * Can be invoked with command line argument of -d or -debug to print additional debug materials, if you're into that sort of thing.    //Todo Keep and implement Log4J2? Make two versions
 * Can be invoked with any number of command line Integer arguments, will pair available command line arguments and print their resultant before moving on.
 * If invoked with an uneven amount of Integer arguments or none at all will prompt user for inputs before completing that specified run.
 * At any available user input if the user input is just a return character exit the program. // Todo keep counter of successful runs?
 **/
public class PrimeGenerator {
    private static final Logger LOGGER = LogManager.getLogger(PrimeGenerator.class);
    private static List<String> arguments;

    public static void main(String[] args) {
        arguments = Arrays.asList(args);
        if(arguments.contains("-d")){
            Helper.setLog4JLogLevels(Level.DEBUG);
        }
        Iterator<String> argumentIterator = arguments.iterator();
        LOGGER.info("TEST");
    }
}
