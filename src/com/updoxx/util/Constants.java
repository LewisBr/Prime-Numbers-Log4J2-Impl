package com.updoxx.util;

/**
 * Constants utility class. Houses a collection of application utility strings and messages.
 */
public class Constants {
    // Constants to determine if paired run was successful or not.
    public final static String SUCCESS = "SUCCESS";
    public final static String ERROR   = "ERROR";

    // Error Messages
    public final static String ERR_NEGNUMBER = "Provided input must both be non-negative Integers values: '@VAL', '@VAL'";
    public final static String ERR_MAXIMUM   = "Provided input must be below 2147483646 (2^32-1).";

    /**
     * Static Message Text
     **/
    // @formatter:off for legibility
    // Static text used at start of application to welcome new users and give instructions
    public final static String INSTRUCTIONS =
        "////////////////////////////////\n" +
        "// Updoxx Coding Challenge #2 //\n" +
        "//   Prime Number Generator   //\n" +
        "////////////////////////////////\n" +
        "// Usage: Use command prompt to input two integer values to generate a list of all prime numbers between the two inputs.\n" +
        "// If after accidentally entering a first number you can enter 'b' or '-b' to re-enter the first value\n" +
        "// To exit the program just hit ENTER or the return character during any period you can input a value.\n" +
        "////////////////////////////////";

    // Static text used for -h | -help for a pseduo-manual page.
    public final static String HELP =
            "## Help Text ##\n" +
            "# This is a command line based utility that uses user prompts to generate a list of Integers that are primes between two given inputs.\n" +
            "# Numbers must be alphanumeric and non-negative, and fall between the values of 0 and INTEGER.MAX_VALUE (2,147,483,646).\n" +
            "# Can be invoked with command line argument of '-h' or '-help' to print manual page before usage.\n" +
            "# Can be invoked with command line argument of '-d' or '-debug' to print additional debug materials, if you're into that sort of thing.\n" +
            "# Can be invoked with any number of command line Integer arguments, will pair available command line arguments and print their resultant before moving on.\n" +
            "# If invoked with an uneven amount of Integer arguments or none at all will prompt user for inputs before completing that specified run.\n" +
            "# At any available user input if the user inputs 'b' or '-b' the program will attempt to gather the first number again.\n" +
            "# At any available user input if the user input is just a return character exit the program.\n" +
            "###############";

    /** Input Prompts **/
    public final static String INPUT_ONEREADY   = "## Please Input Your First Integer";
    public final static String INPUT_TWOREADY   = "## Please Input Your Second Integer -- First value = '{}'";
    public final static String INPUT_INVALID    = "## Input '{}' Is Invalid, Please Try A Different Number";
    public final static String INPUT_RESETSTATE = "## Input '{}' for reset state triggered.";

    /** Run Specific Blocks **/
    // Error block Unknown-- used as a fail safe check, should never be shown..
    final static String ERRORBLOCK_UNKNOWN  = "## Well this is embarrassing... Unknown error.";

    // Error block -- used for displaying error reason.
    final static String ERRORBLOCK    =
            "##########\n"  +
            "## ERROR ## \n" +
            "## Reason: {}\n" +
            "###########";
    // Success block (No Debug) -- Prints out resultant from run.
    final static String SUCCESSBLOCK   =
            "###########\n" +
            "## Prime Numbers Between: {} -> {}\n" +
            "## {}\n" +
            "#############";
    // Success block (with -d) -- Prints out resultant from run as well as how long the calculation took.
    final static String SUCCESSBLOCK_DEBUG   =
            "###########\n" +
            "## Prime Numbers Between: {} -> {}\n" +
            "## {}\n" +
            "## Processed in: {}(ms)\n" +
            "#############";

    public final static String EXITTEXT  = "Thanks for your consideration -- Exiting";
    // @formatter:on
}
