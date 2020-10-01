package edu.kit.informatik;

public class Average extends OneValueState {

    /**
     * @see State
     * 
     * @param name
     *            the name of the state
     */
    public Average(String name) {
        super(name);
    }

    @Override
    public String getAggregation() {
        return "avg";
    }

    @Override
    public double aggregateValue(Day day) {
        double aV = 0;
        for (int i = 0; i < super.data.get(day).size(); i++) {
            aV += super.data.get(day).get(i);
        }
        aV = aV / super.data.get(day).size();
        return aV;
    }
}
