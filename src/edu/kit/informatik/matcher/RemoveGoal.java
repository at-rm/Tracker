package edu.kit.informatik.matcher;

import edu.kit.informatik.StateTracker;
import edu.kit.informatik.Terminal;

public class RemoveGoal extends Command {

    @Override
    public void execute(StateTracker st, String input) {
        Terminal.printLine(st.removeGoal(input.split(" ")[1]));
    }

    @Override
    public String regexMatcher() {
        return "remove-goal [a-z]+";
    }
}
