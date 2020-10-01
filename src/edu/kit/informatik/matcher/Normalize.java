package edu.kit.informatik.matcher;

import edu.kit.informatik.StateTracker;
import edu.kit.informatik.Terminal;

public class Normalize extends Command {

    @Override
    public void execute(StateTracker st, String input) {
        Terminal.printLine(st.normalize(Double.parseDouble(input.split(" ")[1]),
                Double.parseDouble(input.split(" ")[2]), super.parseData(input.split(" ")[3].split(";"))));
    }

    @Override
    public String regexMatcher() {
        return "normalize " + super.doubleRegex() + " " + super.doubleRegex() + " " + super.dataRegex();
    }

}
