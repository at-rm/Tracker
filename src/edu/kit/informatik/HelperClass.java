package edu.kit.informatik;

import java.util.ArrayList;
import java.util.List;

public final class HelperClass {

    private HelperClass() {
        //
    }

    /**
     * Counts how many states in the StateTracker have a goal and then returns
     * an output.
     * 
     * @param st
     *            the StateTracker
     * @return "Number of goals: {@literal <goals>}"
     */
    public static String getNumberOfGoals(StateTracker st) {
        int goals = 0;
        for (int i = 0; i < st.getStates().size(); i++) {
            if (st.getStates().get(i).getGoal() != null) {
                goals++;
            }
        }
        return "Number of goals: " + goals;
    }

    /**
     * Gets all the goals in the StateTracker and then returns an output.
     * 
     * @param st
     *            the StateTracker
     * @return every line is "{@literal <type>: <goal description>}"
     */
    public static String getGoals(StateTracker st) {
        String goals = "";
        for (int i = 0; i < st.getStates().size(); i++) {
            if (st.getStates().get(i).getGoal() != null) {
                goals += "\n" + st.getStates().get(i).getName() + ": " + st.getStates().get(i).getGoal().toString();
            }
        }
        return goals;
    }

    /**
     * For every state, it checks how many times the goal has been reached in
     * the Week numOfWeek.
     * 
     * @param st
     *            the StateTracker
     * @param numOfWeek
     *            the number of the week
     * @return every line is
     *         "{@literal <type>: <goal description> - <times goal has been reached in the week>}"
     */
    public static String goalsReachedForEveryState(StateTracker st, int numOfWeek) {
        String output = "";
        for (int i = 0; i < st.getStates().size(); i++) {
            int goalReached = 0;
            for (int j = 7 * (numOfWeek - 1); j < 7 * numOfWeek; j++) {
                if (st.getStates().get(i).goalReached(new Day(new Week(numOfWeek), j))) {
                    goalReached++;
                }
            }
            output += "\n" + st.getStates().get(i).getName() + ": " + st.getStates().get(i).getGoal().toString() + " - "
                    + goalReached;
        }
        return output;
    }

    /**
     * If there is no entry for one or more States, this method returns it.
     * 
     * @param st
     *            the StateTracker
     * @return every line is "{@literal no entry for <type>}"
     */
    public static String noEntry(StateTracker st) {
        String output = "";
        for (int i = 0; i < st.getStates().size(); i++) {
            if (!st.getStates().get(i).valuesRecorded(st.getDay())) {
                if (!output.equals("")) {
                    output += "\n" + "no entry for " + st.getStates().get(i).getName();
                } else {
                    output += "no entry for " + st.getStates().get(i).getName();
                }
            }
        }
        return output;
    }

    /**
     * Checks how many goals there are overall and how many were reached and
     * then returns an output
     * 
     * @param st
     *            the StateTracker
     * @return "{@literal Goals reached: <goals reached on current day>/<set goals>}"
     */
    public static String goalsReached(StateTracker st) {
        int goalsOverall = 0;
        int goalsReached = 0;
        for (int i = 0; i < st.getStates().size(); i++) {
            if (st.getStates().get(i).getGoal() != null) {
                goalsOverall++;
                if (st.getStates().get(i).goalReached(st.getDay())) {
                    goalsReached++;
                }
            }
        }
        return "Goals reached: " + goalsReached + "/" + goalsOverall;
    }

    /**
     * Calculates what the next day should be.
     * 
     * @param st
     *            the StateTracker
     * @return the next day after the current day in the StateTracker
     */
    public static Day getNewDay(StateTracker st) {
        int currentDay = st.getDay().getNumberOfDay();
        Day next = new Day(new Week((currentDay + 1) / 7 + 1), currentDay + 1);
        return next;
    }

    /**
     * Finds the number of the state in the states ArrayList.
     * 
     * @param st
     *            the StateTracker
     * @param stateName
     *            the name of the state
     * @return either the number of the state in states or -1 if the state
     *         wasn't found
     */
    public static int findState(StateTracker st, String stateName) {
        for (int i = 0; i < st.getStates().size(); i++) {
            if (st.getStates().get(i).getName().equals(stateName)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Converts a double value into a String output.
     * 
     * @param value
     *            the value to be converted
     * @return decimal number with at least two decimal places
     */
    public static String valueToString(double value) {
        String output = Double.toString(value);
        if (Double.isNaN(value) || Double.isInfinite(value)) {
            return output;
        }
        String s1 = output.split("\\.")[0];
        String s2 = output.split("\\.")[1];
        while (s2.length() < 2) {
            s2 += "0";
        }
        return s1 + "." + s2;
    }

    /**
     * Converts data into a printable String.
     * 
     * @param data
     *            the data to be printed
     * @return the data as a String
     */
    public static String printData(double[] data) {
        String output = "";
        for (int i = 0; i < data.length; i++) {
            output += valueToString(data[i]);
            if (i != data.length - 1) {
                output += ";";
            }
        }
        return output;
    }

    /**
     * Takes n values of data, calculates the average of those and then returns
     * the new values.
     * 
     * @param n
     *            sliding window size
     * @param data
     *            the data that is smoothed
     * @return see above.
     */
    public static double[] slidingWindowData(int n, double[] data) {
        double[] sliddenData = new double[data.length - (n - 1)];
        for (int i = 0; i < sliddenData.length; i++) {
            for (int j = i; j < i + n; j++) {
                sliddenData[i] = sliddenData[i] + data[j];
            }
            sliddenData[i] = sliddenData[i] / n;
        }
        return sliddenData;
    }

    /**
     * Normalizes the data by scaling it in an interval from smallest to
     * largest.
     * 
     * @param smallest
     *            the lower interval border
     * @param largest
     *            the upper interval border
     * @param data
     *            the data to be normalized
     * @return the normalized data as a double array
     */
    public static double[] normalizeData(double smallest, double largest, double[] data) {
        double min = findMinimumInData(data);
        double max = findMaximumInData(data);
        double[] normalizedData = new double[data.length];
        for (int i = 0; i < data.length; i++) {
            normalizedData[i] = findNormalizedData(data[i], smallest, largest, min, max);
        }
        return normalizedData;
    }

    private static double findMinimumInData(double[] data) {
        double min = data[0];
        for (double d : data) {
            if (d < min) {
                min = d;
            }
        }
        return min;
    }

    private static double findMaximumInData(double[] data) {
        double max = data[0];
        for (double d : data) {
            if (d > max) {
                max = d;
            }
        }
        return max;
    }

    private static double findNormalizedData(double value, double smallest, double largest, double min, double max) {
        // solved the formula from B.2.3 for gamma
        return (((value - min) * (largest - smallest)) / (max - min)) + smallest;
    }

    /**
     * Finds the peaks of a set of data.
     * 
     * @param threshold
     *            the threshold for the peak
     * @param n
     *            the length of the streak
     * @param data
     *            the data in which the peaks are to be found
     * @return the list with the index when the peak begins
     */
    public static List<Integer> findPeaks(double threshold, int n, double[] data) {
        List<Integer> peaks = new ArrayList<>();
        int streak = 0;
        for (int i = 0; i < data.length; i++) {
            if (data[i] > threshold) {
                streak++;
            } else {
                streak = 0;
            }
            if (streak == n) {
                peaks.add(i + 1 - n);
            }
        }
        return peaks;
    }
}
