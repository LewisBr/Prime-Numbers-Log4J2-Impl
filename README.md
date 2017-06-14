# Updoxx Code Challenge #2 - Prime Number Generator

This implementation roughly follows the guidelines set by the documentation, with some liberties taken to add some additional functionality and clarity.
In this implementation I added Log4J2 to allow for some additional logging mechanics and efficiency, as a result this can still be run by the command line but you need to add "-cp /lib/*" to avoid
dependency issues.
If you would like to avoid the hassle of that I've included a pre-compiled .jar (Updoxx-CC-Prime-Numbers.jar) in the project root, and to run it from command line just use the following input:

java -jar Updoxx-CC-Prime-Numbers.jar [options]

Where options can be any of the following:
-h: prints a help manual that provides additional details about how the application works.
-d: debug mode, will print additional details about specific runs. Including how long individual run takes.
-b: resets state to allow the first number to be inserted again.
Any number of Integer values, they will be paired and executed accordingly.

After the initial run is complete, the program prompts the user for inputs to continue, if the user decides to stop they can just select Enter and it exits the program.
