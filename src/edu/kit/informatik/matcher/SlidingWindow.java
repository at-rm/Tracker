package edu.kit.informatik.matcher;

import edu.kit.informatik.StateTracker;
import edu.kit.informatik.Terminal;

public class SlidingWindow extends Command {

    @Override
    public void execute(StateTracker st, String input) {
        Terminal.printLine(st.slidingWindow(Integer.parseInt(input.split(" ")[1]),
                super.parseData(input.split(" ")[2].split(";"))));
    }

    @Override
    public String regexMatcher() {
        return "sliding-window [1-9]+[0-9]* " + super.dataRegex();
    }

}
