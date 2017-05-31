package com.updoxx.util;

/**
 * Created by Bryan on 5/30/2017.
 */
public class Constants {
    // Constants to determine if paired run was successful or not.
    public final static String SUCCESS = "SUCCESS";
    public final static String ERROR   = "ERROR";

    ///////////////////
    // MESSAGE TEXTS //
    ///////////////////
    // @formatter:off for legibility
    // Static text used at start of application to welcome new users and give instructions
    public final static String INSTRUCTIONS =
        "////////////////////////////////" +
        "// Updoxx Coding Challenge #1 //" +
        "//   Prime Number Generator   //" +
        "////////////////////////////////" +
        "// Usage: Use command prompt to input two integer values to generate a list of all prime numbers between the two inputs." +
        "// To exit the program just hit ENTER or the return character during any period you can input a value.";

    /** Input Prompts **/
    public final static String INPUTRONEEADY = "## Please Input Your First Integer  ##";
    public final static String INPUTTWOREADY = "## Please Input Your Second Integer ##";
    public final static String INPUTINVALID  = "## INPUT '%VALUE%' Is Invalid, Please Try A Different Number ##";

    /** Run Specific Blocks **/
    // Error block -- used for displaying error reason.
    public final static String ERRORBLOCK    =
            "##########"  +
            "## ERROR ##" +
            "## Reason: %REASON% ##" +
            "###########";
    // Success block (No Debug) -- Prints out resultant from run.
    public final static String SUCCESSBLOCK   =
            "###########" +
            "## Prime Numbers Between: %VAL1% -> %VAL2% ##" +
            "## %ARRAY% ##" +
            "#############";
    // Success block (with -d) -- Prints out resultant from run as well as how long the calculation took.
    public final static String SUCCESSBLOCK_DEBUG   =
            "###########" +
            "## Prime Numbers Between: %VAL1% -> %VAL2% ##" +
            "## %ARRAY% ##" +
            "## Processed in: %MS%(ms) ##" +
            "#############";

    public final static String EXITTEXT      = "\nThank your consideration -- Exiting";
    // @formatter:on
}
