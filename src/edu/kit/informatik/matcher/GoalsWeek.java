package edu.kit.informatik.matcher;

import edu.kit.informatik.StateTracker;
import edu.kit.informatik.Terminal;

public class GoalsWeek extends Command {

    @Override
    public void execute(StateTracker st, String input) {
        Terminal.printLine(st.goalsWeek(Integer.parseInt(input.split(" ")[1])));
    }

    @Override
    public String regexMatcher() {
        return "goals-week [1-9]+[0-9]*";
    }

}
