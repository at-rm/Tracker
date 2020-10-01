package edu.kit.informatik.matcher;

import edu.kit.informatik.StateTracker;
import edu.kit.informatik.Terminal;

public class PulseData extends Command {

    @Override
    public void execute(StateTracker st, String input) {
        Terminal.printLine(st.pulse(super.parseData(input.split(" ")[1].split(";"))));
    }

    @Override
    public String regexMatcher() {
        return "pulse " + super.dataRegex();
    }

}
