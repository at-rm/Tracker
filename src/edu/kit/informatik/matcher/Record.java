package edu.kit.informatik.matcher;

import edu.kit.informatik.StateTracker;
import edu.kit.informatik.Terminal;

public class Record extends Command {

    @Override
    public void execute(StateTracker st, String input) {
        Terminal.printLine(st.record(input.split(" ")[1], Double.parseDouble(input.split(" ")[2])));
    }

    @Override
    public String regexMatcher() {
        return "record [a-z]+ " + super.doubleRegex();
    }

}
