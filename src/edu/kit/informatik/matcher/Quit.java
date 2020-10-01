package edu.kit.informatik.matcher;

import edu.kit.informatik.StateTracker;
import edu.kit.informatik.Terminal;

public class Quit extends Command {

    @Override
    public void execute(StateTracker st, String input) {
        Terminal.printLine("Quit (" + st.getDay().toString() + ")");
    }

    @Override
    public String regexMatcher() {
        return "quit";
    }
}
