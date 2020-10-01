package edu.kit.informatik;

public class Greater extends Goal {

    private double value;

    /**
     * The goal is to have an aggregated value greater than value.
     * 
     * @param value
     *            the lower border
     */
    public Greater(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "greater-than " + HelperClass.valueToString(value);
    }

    @Override
    public boolean oneValueReached(double av) {
        return av > value;
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
        return minAv > value;
    }

    @Override
    public String twoValueInput(double minAv, double maxAv) {
        if (twoValueReached(minAv, maxAv)) {
            return "goal reached";
        }
        return "goal not reached";
    }

}
