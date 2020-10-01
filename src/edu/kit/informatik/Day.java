package edu.kit.informatik;

public class Day implements Comparable<Day> {

    private Week week;
    private int numberOfDay;

    /**
     * Creates a day in the Week week and at the day numberOfDay.
     * 
     * @param week
     *            the week in which the day is
     * @param numberOfDay
     *            the number of the day since start
     */
    Day(Week week, int numberOfDay) {
        this.setWeek(week);
        this.setNumberOfDayInWeek(numberOfDay);
    }

    /**
     * Simple getter.
     * 
     * @return the number of the day since the beginning
     */
    public int getNumberOfDay() {
        return numberOfDay;
    }

    /**
     * Simple setter.
     * 
     * @param numberOfDay
     *            the number of the day since the beginning
     */
    public void setNumberOfDayInWeek(int numberOfDay) {
        this.numberOfDay = numberOfDay;
    }

    /**
     * Simple getter.
     * 
     * @return the week in which the day is
     */
    public Week getWeek() {
        return week;
    }

    /**
     * Simple setter.
     * 
     * @param week
     *            the week in which the day is
     */
    public void setWeek(Week week) {
        this.week = week;
    }

    @Override
    public String toString() {
        return "Day " + numberOfDay + ", week " + week.getNumberOfWeek();
    }

    @Override
    public int compareTo(Day o) {
        if (this.getNumberOfDay() > o.getNumberOfDay()) {
            return 1;
        }
        if (this.getNumberOfDay() < o.getNumberOfDay()) {
            return -1;
        }
        return 0;
    }
}
