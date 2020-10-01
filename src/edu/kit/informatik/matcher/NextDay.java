package edu.kit.informatik.matcher;

import edu.kit.informatik.StateTracker;
import edu.kit.informatik.Terminal;

public class NextDay extends Command {

    @Override
    public void execute(StateTracker st, String input) {
        Terminal.printLine(st.nextDay());
    }

    @Override
    public String regexMatcher() {
        return "next-day";
    }

}
