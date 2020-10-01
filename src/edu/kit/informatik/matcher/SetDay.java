package edu.kit.informatik.matcher;

import edu.kit.informatik.StateTracker;
import edu.kit.informatik.Terminal;

public class SetDay extends Command {

    @Override
    public void execute(StateTracker st, String input) {
        Terminal.printLine(st.setDayOutput(Integer.parseInt(input.split(" ")[1])));
    }

    @Override
    public String regexMatcher() {
        return "set-day [0-9]+";
    }

}
