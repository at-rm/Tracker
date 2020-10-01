package edu.kit.informatik.matcher;

import edu.kit.informatik.StateTracker;
import edu.kit.informatik.Terminal;

public class Peaks extends Command {

    @Override
    public void execute(StateTracker st, String input) {
        Terminal.printLine(st.peaks(Double.parseDouble(input.split(" ")[1]), Integer.parseInt(input.split(" ")[2]),
                super.parseData(input.split(" ")[3].split(";"))));
    }

    @Override
    public String regexMatcher() {
        return "peaks " + super.doubleRegex() + " [1-9]+[0-9]* " + super.dataRegex();
    }

}
