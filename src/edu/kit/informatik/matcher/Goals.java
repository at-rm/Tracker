package edu.kit.informatik.matcher;

import edu.kit.informatik.StateTracker;
import edu.kit.informatik.Terminal;

public class Goals extends Command {

    @Override
    public void execute(StateTracker st, String input) {
        Terminal.printLine(st.goals());
    }

    @Override
    public String regexMatcher() {
        return "goals";
    }

}
