package edu.kit.informatik;

public abstract class Goal {

    /**
     * Check the concrete Goal classes.
     */
    Goal() {
        //
    }

    @Override
    public abstract String toString();

    /**
     * Checks if the goal is reached, when the input is only one double value.
     * 
     * @param av
     *            the value to be checked
     * @return see above.
     */
    public abstract boolean oneValueReached(double av);

    /**
     * Checks if the goal is reached by calling oneValueReached and then
     * produces an output.
     * 
     * @param av
     *            the value to be checked
     * @return "goal reached" or "goal not reached"
     */
    public abstract String oneValueInput(double av);

    /**
     * Checks if the goal is reached when the input is two double values.
     * 
     * @param minAv
     *            the lower value to be checked
     * @param maxAv
     *            the higher value to be checked
     * @return see above.
     */
    public abstract boolean twoValueReached(double minAv, double maxAv);

    /**
     * Checks if the goal is reached by calling twoValueReached and then
     * produces an output.
     * 
     * @param minAv
     *            the lower value to be checked
     * @param maxAv
     *            the higher value to be checked
     * @return see above.
     */
    public abstract String twoValueInput(double minAv, double maxAv);
}
