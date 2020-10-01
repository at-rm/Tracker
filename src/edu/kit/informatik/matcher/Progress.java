package edu.kit.informatik.matcher;

import edu.kit.informatik.StateTracker;
import edu.kit.informatik.Terminal;

public class Progress extends Command {

    @Override
    public void execute(StateTracker st, String input) {
        Terminal.printLine(st.progress());
    }

    @Override
    public String regexMatcher() {
        return "progress";
    }

}
