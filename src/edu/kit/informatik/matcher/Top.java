package edu.kit.informatik.matcher;

import edu.kit.informatik.StateTracker;
import edu.kit.informatik.Terminal;

public class Top extends Command {

    @Override
    public void execute(StateTracker st, String input) {
        Terminal.printLine(st.top(Integer.parseInt(input.split(" ")[1]), input.split(" ")[2]));
    }

    @Override
    public String regexMatcher() {
        return "top [1-9]+[0-9]* [a-z]+";
    }
}
