package edu.kit.informatik;

import java.util.ArrayList;
import java.util.List;

public abstract class OneValueState extends State {

    /**
     * A state that only has one aggregated value.
     * 
     * @param name
     *            the name of the state
     */
    OneValueState(String name) {
        super(name);
    }

    @Override
    public abstract String getAggregation();

    /**
     * Aggregates the value of the day according to the definitions of the
     * specific states.
     * 
     * @param day
     *            the day on which the value is to be aggregated
     * @return the aggregated value
     */
    public abstract double aggregateValue(Day day);

    @Override
    public String aggregatedValue(Day day) {
        if (super.data.get(day) == null) {
            return "none";
        }
        double aV = aggregateValue(day);
        return HelperClass.valueToString(aV);
    }

    @Override
    public boolean goalReached(Day day) {
        if (super.data.get(day) == null) {
            return false;
        }
        return super.getGoal().oneValueReached(aggregateValue(day));
    }

    @Override
    public String goalReachedOutput(Day day) {
        Goal g = super.getGoal();
        if (g == null) {
            return "no goal";
        }
        String output = g.oneValueInput(aggregateValue(day));
        return output;
    }

    @Override
    public List<Day> daysSorted(Day day) {
        List<Day> daysSorted = new ArrayList<>();
        List<Day> daysUnsorted = new ArrayList<>();
        for (int i = 0; i < day.getNumberOfDay() + 1; i++) {
            if (super.data.get(new Day(new Week(i / 7 + 1), i)) != null) {
                daysUnsorted.add(new Day(new Week(i / 7 + 1), i));
            }
        }
        while (!daysUnsorted.isEmpty()) {
            Day highestDay = daysUnsorted.get(0);
            int index = 0;
            for (int j = 0; j < daysUnsorted.size(); j++) {
                if (aggregateValue(daysUnsorted.get(j)) > aggregateValue(highestDay)) {
                    highestDay = daysUnsorted.get(j);
                    index = j;
                }
            }
            daysSorted.add(highestDay);
            daysUnsorted.remove(index);
        }
        return daysSorted;
    }

    @Override
    public String daysSortedOutput(Day day, int n) {
        List<Day> daysSorted = daysSorted(day);
        if (daysSorted.isEmpty()) {
            return "Error, no data available.";
        }
        String output = "";
        for (int i = 0; i < n && i < daysSorted.size(); i++) {
            output += "Day " + daysSorted.get(i).getNumberOfDay() + " - "
                    + HelperClass.valueToString((aggregateValue(daysSorted.get(i))));
            if (i < n - 1 && i < daysSorted.size() - 1) {
                output += "\n";
            }
        }
        return output;
    }

}
