package edu.kit.informatik;

public class Between extends Goal {

    private double min;
    private double max;

    /**
     * The goal is to have an aggregated value greater than min and lower than
     * max.
     * 
     * @param min
     *            the lower border
     * @param max
     *            the upper border
     */
    public Between(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public String toString() {
        return "between " + HelperClass.valueToString(min) + " and " + HelperClass.valueToString(max);
    }

    @Override
    public boolean oneValueReached(double av) {
        return av > min && av < max;
    }

    @Override
    public String oneValueInput(double av) {
        if (oneValueReached(av)) {
            return "goal reached";
        }
        return "goal not reached";
    }

    @Override
    public boolean twoValueReached(double minAv, double maxAv) {
        return minAv > min && maxAv < max;
    }

    @Override
    public String twoValueInput(double minAv, double maxAv) {
        if (twoValueReached(minAv, maxAv)) {
            return "goal reached";
        }
        return "goal not reached";
    }

}
