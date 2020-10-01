package edu.kit.informatik;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public abstract class State {

    /**
     * The map that contains the day and the values recorded on that day.
     */
    protected Map<Day, ArrayList<Double>> data = new TreeMap<>();
    private String name;
    private Goal goal;

    /**
     * Creates a State with a name.
     * 
     * @param name
     *            the name of the state
     */
    State(String name) {
        this.setName(name);
    }

    /**
     * Simple getter.
     * 
     * @return the goal for the state
     */
    public Goal getGoal() {
        return goal;
    }

    /**
     * Simple setter.
     * 
     * @param g
     *            the goal for the state
     */
    public void setGoal(Goal g) {
        this.goal = g;
    }

    /**
     * @return "sum" or "avg" or "last" or "minmax"
     */
    public abstract String getAggregation();

    /**
     * Simple getter.
     * 
     * @return the name of the state
     */
    public String getName() {
        return name;
    }

    /**
     * Simple setter.
     * 
     * @param name
     *            the name of the state
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Records a value for a day.
     * 
     * @param day
     *            the day for which the value is recorded
     * @param value
     *            the value that is recorded
     */
    public void recordValue(Day day, double value) {
        ArrayList<Double> valuesForTheDay = new ArrayList<>();
        if (data.get(day) != null) {
            valuesForTheDay = data.get(day);
        }
        valuesForTheDay.add(value);
        data.put(day, valuesForTheDay);
    }

    /**
     * Creates an aggregated value for the Day day and then returns an output
     * for it.
     * 
     * @param day
     *            the day for which the aggregated value is requested
     * @return {@literal <type>: <value>}
     */
    public abstract String aggregatedValue(Day day);

    /**
     * Checks if a value has been recorded on the Day day.
     * 
     * @param day
     *            the day to be checked
     * @return see above.
     */
    public boolean valuesRecorded(Day day) {
        return data.get(day) != null;
    }

    /**
     * Checks if the goal has been reached on the Day day.
     * 
     * @param day
     *            the day to be checked
     * @return see above.
     */
    public abstract boolean goalReached(Day day);

    /**
     * Creates an output for whether or not a goal was reached.
     * 
     * @param day
     *            the day to be checked
     * @return "goal reached" or "goal not reached".
     */
    public abstract String goalReachedOutput(Day day);

    /**
     * Sorts the days by highest value.
     * 
     * @param day
     *            every day to be sorted has to be before that day
     * @return the sorted days
     */
    public abstract List<Day> daysSorted(Day day);

    /**
     * Creates an output for daysSorted
     * 
     * @param day
     *            current day
     * @param n
     *            the amount of days/values to be returned
     * @return every line is {@literal Day <day number> - <aggregated value>}
     */
    public abstract String daysSortedOutput(Day day, int n);
}
