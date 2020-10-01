package edu.kit.informatik;

import java.util.ArrayList;
import java.util.List;

public class StateTracker {

    private List<State> states = new ArrayList<>();
    private Day day;

    /**
     * Initiates the StateTracker by adding the MinMax-State pulse and starting
     * the day-counter at day 0 and week 1.
     */
    StateTracker() {
        this.states.add(new MinMax("pulse"));
        this.day = new Day(new Week(1), 0);
    }

    /**
     * Adds a state if it doesn't exist yet.
     * 
     * @param state
     *            the state to be added
     * @return "{@literal Added <type> with aggregation <aggregation>.}"
     */
    public String addStateType(State state) {
        if (HelperClass.findState(this, state.getName()) != -1) {
            return "Error, " + state.getName() + " already exists.";
        }
        states.add(state);
        return "Added " + state.getName() + " with aggregation " + state.getAggregation() + ".";
    }

    /**
     * Adds a goal to a state.
     * 
     * @param stateName
     *            the state the goal is added to
     * @param goal
     *            the goal to be added
     * @return "{@literal New goal for <type>: <goal description>.}"
     */
    public String addGoal(String stateName, Goal goal) {
        if (HelperClass.findState(this, stateName) == -1) {
            return "Error, " + stateName + " doesn't exist.";
        }
        states.get(HelperClass.findState(this, stateName)).setGoal(goal);
        return "New goal for " + stateName + ": " + goal.toString() + ".";
    }

    /**
     * Removes the goal from a state.
     * 
     * @param stateName
     *            the name of the state the goal is removed from
     * @return "{@literal Removed goal <type>.}"
     */
    public String removeGoal(String stateName) {
        if (HelperClass.findState(this, stateName) == -1) {
            return "Error, " + stateName + " doesn't exist.";
        }
        if (states.get(HelperClass.findState(this, stateName)).getGoal() == null) {
            return "Error, no goal found.";
        }
        states.get(HelperClass.findState(this, stateName)).setGoal(null);
        return "Removed goal for " + stateName + ".";
    }

    /**
     * Shows the goals of all states.
     * 
     * @return see the HelperClass methods for this info
     */
    public String goals() {
        return HelperClass.getNumberOfGoals(this) + HelperClass.getGoals(this);
    }

    /**
     * Records a value for a state.
     * 
     * @param stateName
     *            the name of the state the value is added to
     * @param value
     *            the value to be added
     * @return "{@literal <type>: <new aggregated value>}"
     */
    public String record(String stateName, double value) {
        if (HelperClass.findState(this, stateName) == -1) {
            return "Error, " + stateName + " doesn't exist.";
        }
        states.get(HelperClass.findState(this, stateName)).recordValue(day, value);
        return stateName + ": " + states.get(HelperClass.findState(this, stateName)).aggregatedValue(day);
    }

    /**
     * Returns all the values with their aggregated values of the current day.
     * 
     * @return every line is "{@literal <type>: <value>}"
     */
    public String state() {
        String state = "";
        for (int i = 0; i < states.size(); i++) {
            state += states.get(i).getName() + ": " + states.get(i).aggregatedValue(day);
            if (i != states.size() - 1) {
                state += "\n";
            }
        }
        return state;
    }

    /**
     * Shows the progress for the current day.
     * 
     * @return first line: "{@literal Day <current day>, week <current week>}"
     *         every following line:
     *         "{@literal <type>: <value> - <whether goal was reached/if there was no goal>}"
     */
    public String progress() {
        String output = "";
        for (int i = 0; i < states.size(); i++) {
            output += "\n" + states.get(i).getName() + ": " + states.get(i).aggregatedValue(day);
            if (states.get(i).aggregatedValue(day).equals("none") && states.get(i).getGoal() != null) {
                output += " - goal not reached";
            } else {
                output += " - " + states.get(i).goalReachedOutput(day);
            }
        }
        return day.toString() + output;
    }

    /**
     * Returns the n days on which the value for a selected state was highest
     * 
     * @param n
     *            the days
     * @param stateName
     *            the name of the state
     * @return every line: "{@literal Day <day number> - <aggregated value>}"
     */
    public String top(int n, String stateName) {
        if (HelperClass.findState(this, stateName) == -1) {
            return "Error, " + stateName + " doesn't exist.";
        }
        return states.get(HelperClass.findState(this, stateName)).daysSortedOutput(day, n);
    }

    /**
     * Returns how many times each goal was reached in the numOfWeekth week.
     * 
     * @param numOfWeek
     *            the number of the week
     * @return first line:
     *         "{@literal Number of goals: <current number of goals>}" every
     *         following line :
     *         "{@literal <type>: <goal description> - <times goal was reached>}"
     */
    public String goalsWeek(int numOfWeek) {
        String output = HelperClass.getNumberOfGoals(this);
        output += HelperClass.goalsReachedForEveryState(this, numOfWeek);
        return output;
    }

    /**
     * If every state has an entry for the day, the day counter is increased.
     * Returns how many goals were reached on the current day and what the next
     * day is.
     * 
     * @return first line:
     *         "{@literal Goals reached: <goals reached on day>/<goals overall>}"
     *         second line:
     *         "{@literal Day <next day>, week <week for the next day>}"
     */
    public String nextDay() {
        String output = HelperClass.noEntry(this);
        if (!output.equals("")) {
            return output;
        }
        output += HelperClass.goalsReached(this);
        day = HelperClass.getNewDay(this);
        output += "\n" + day.toString();
        return output;
    }

    /**
     * Sets the day on a different day, if the day is a valid day.
     * 
     * @param i
     *            the absolute number of the day (since the beginning)
     * @return "{@literal Day <new day value>, week <new week value>}"
     */
    public String setDayOutput(int i) {
        if (i < 0) {
            return "Error, not a valid day.";
        }
        setDay(new Day(new Week(i / 7 + 1), i));
        return day.toString();
    }

    /**
     * Calls the slidingWindowData method and then prints the results.
     * 
     * @param n
     *            see slidingWindowData
     * @param data
     *            seeslidingWindowData
     * @return the new smoothed values
     */
    public String slidingWindow(int n, double[] data) {
        if (n > data.length) {
            return "Error, n has to be smaller or equal to the amout of data.";
        }
        double[] sliddenData = HelperClass.slidingWindowData(n, data);
        return HelperClass.printData(sliddenData);
    }

    /**
     * @see HelperClass
     * 
     * @param smallest
     *            the lower border of the interval
     * @param largest
     *            the upper border of the interval
     * @param data
     *            the data to be normalized
     * @return the normalized data
     */
    public String normalize(double smallest, double largest, double[] data) {
        if (smallest >= largest) {
            return "Error, the smallest can't be larger than the largest.";
        }
        if (data.length < 2) {
            return "Error, this doesn't make sense with less than 2 values.";
        }
        double[] normalizedData = HelperClass.normalizeData(smallest, largest, data);
        return HelperClass.printData(normalizedData);
    }

    /**
     * Calls findPeaks and then creates an output that will be printed.
     * 
     * @param threshold
     *            see findPeaks
     * @param n
     *            see findPeaks
     * @param data
     *            see findPeaks
     * @return "{@literal <peak 1>...<peak n>}"
     */
    public String peaks(double threshold, int n, double[] data) {
        List<Integer> peaks = HelperClass.findPeaks(threshold, n, data);
        String output = "";
        for (int i = 0; i < peaks.size(); i++) {
            output += peaks.get(i);
            if (i < peaks.size() - 1) {
                output += ";";
            }
        }
        if (output.equals("")) {
            return "no peaks found";
        }
        return output;
    }

    /**
     * Takes ECG data and turns it into a heart rate by first putting a sliding
     * window, normalizes it, calculates an average of the peaks and then the
     * magic formula turns it into a heart rate.
     * 
     * @param rawData
     *            the ECG data
     * @return an output that shows the pulse frequency that was recorded and
     *         then adds it to the pulse values
     */
    public String pulse(double[] rawData) {
        double[] data = HelperClass.slidingWindowData(3, rawData);
        data = HelperClass.normalizeData(0, 1, data);
        List<Integer> peaks = HelperClass.findPeaks(0.7, 2, data);
        double avgDistance = 0;
        for (int i = 1; i < peaks.size(); i++) {
            avgDistance += peaks.get(i) - peaks.get(i - 1);
        }
        double heartRate = 0;
        if (peaks.size() > 1) {
            avgDistance = avgDistance / (peaks.size() - 1);
            // reverse of avgDistance * 1/30, multiplied by 60 to convert to
            // minutes
            heartRate = (30 / avgDistance) * 60;
        } else {
            return "Error, no peaks found.";
        }
        return "pulse frequency: " + HelperClass.valueToString(heartRate) + "\n" + record("pulse", heartRate);
    }

    /**
     * Simple getter.
     * 
     * @return the current day
     */
    public Day getDay() {
        return day;
    }

    /**
     * Simple setter.
     * 
     * @param day
     *            the new day
     */
    public void setDay(Day day) {
        this.day = day;
    }

    /**
     * Simple getter.
     * 
     * @return the list with all the states.
     */
    public List<State> getStates() {
        return states;
    }
}
