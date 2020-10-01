package edu.kit.informatik;

public class LastValue extends OneValueState {

    /**
     * @see State
     * 
     * @param name
     *            the name of the state
     */
    public LastValue(String name) {
        super(name);
    }

    @Override
    public String getAggregation() {
        return "last";
    }

    @Override
    public double aggregateValue(Day day) {
        return data.get(day).get(data.get(day).size() - 1);
    }
}
