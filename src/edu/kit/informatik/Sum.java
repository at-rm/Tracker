package edu.kit.informatik;

public class Sum extends OneValueState {

    /**
     * @see State
     * 
     * @param name
     *            the name of the state
     */
    public Sum(String name) {
        super(name);
    }

    @Override
    public String getAggregation() {
        return "sum";
    }

    @Override
    public double aggregateValue(Day day) {
        double aV = 0;
        for (int i = 0; i < super.data.get(day).size(); i++) {
            aV += super.data.get(day).get(i);
        }
        return aV;
    }
}
