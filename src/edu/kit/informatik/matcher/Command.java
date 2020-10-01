package edu.kit.informatik.matcher;

import edu.kit.informatik.StateTracker;

public abstract class Command {

    /**
     * Executes the corresponding command in the StateTracker.
     * 
     * @param st
     *            the StateTracker in which the command is executed.
     * @param input
     *            user input
     */
    public abstract void execute(StateTracker st, String input);

    /**
     * String to check if the user's input matches the corresponding command.
     * 
     * @return see above
     */
    public abstract String regexMatcher();

    /**
     * A regex that checks for a double number.
     * 
     * @return see above.
     */
    protected String doubleRegex() {
        return "([-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?)";
    }

    /**
     * A regex that checks for a data structure.
     * 
     * @return see above.
     */
    protected String dataRegex() {
        return doubleRegex() + "[;" + doubleRegex() + "]*";
    }

    /**
     * Parses an array of data to a double array of data.
     * 
     * @param dataStrings
     *            the data as a String
     * @return the data as a double array
     */
    protected double[] parseData(String[] dataStrings) {
        double[] data = new double[dataStrings.length];
        for (int i = 0; i < dataStrings.length; i++) {
            data[i] = Double.parseDouble(dataStrings[i]);
        }
        return data;
    }
}
