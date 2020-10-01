package edu.kit.informatik;

import java.util.ArrayList;
import java.util.List;

public class MinMax extends State {

    /**
     * @see State
     * 
     * @param name
     *            the name of the state
     */
    public MinMax(String name) {
        super(name);
    }

    @Override
    public String getAggregation() {
        return "minmax";
    }

    @Override
    public String aggregatedValue(Day day) {
        if (data.get(day) == null) {
            return "none";
        }
        return HelperClass.valueToString(findMin(day)) + " to " + HelperClass.valueToString(findMax(day));
    }

    private double findMin(Day day) {
        double min = data.get(day).get(0);
        for (int i = 1; i < data.get(day).size(); i++) {
            if (data.get(day).get(i) < min) {
                min = data.get(day).get(i);
            }
        }
        return min;
    }

    private double findMax(Day day) {
        double max = data.get(day).get(0);
        for (int i = 1; i < data.get(day).size(); i++) {
            if (data.get(day).get(i) > max) {
                max = data.get(day).get(i);
            }
        }
        return max;
    }

    @Override
    public String goalReachedOutput(Day day) {
        Goal g = super.getGoal();
        if (g == null) {
            return "no goal";
        }
        String output = g.twoValueInput(findMin(day), findMax(day));
        return output;
    }

    @Override
    public boolean goalReached(Day day) {
        if (super.data.get(day) == null) {
            return false;
        }
        return super.getGoal().twoValueReached(findMin(day), findMax(day));
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
                if (findMax(daysUnsorted.get(j)) > findMax(highestDay)) {
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
                    + HelperClass.valueToString((findMax(daysSorted.get(i))));
            if (i < n - 1 && i < daysSorted.size() - 1) {
                output += "\n";
            }
        }
        return output;
    }
}
