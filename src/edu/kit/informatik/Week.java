package edu.kit.informatik;

public class Week {

    private int numberOfWeek;

    /**
     * Creates a week, which is the numberOfWeekth week since the beginning.
     * 
     * @param numberOfWeek
     *            the number of the week since the beginning
     */
    Week(int numberOfWeek) {
        this.setNumberOfWeek(numberOfWeek);
    }

    /**
     * Simple getter.
     * 
     * @return the number of the week (>0)
     */
    public int getNumberOfWeek() {
        return numberOfWeek;
    }

    /**
     * Simple setter.
     * 
     * @param numberOfWeek
     *            the number of the week (>0)
     */
    public void setNumberOfWeek(int numberOfWeek) {
        this.numberOfWeek = numberOfWeek;
    }

}
