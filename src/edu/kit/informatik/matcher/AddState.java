package edu.kit.informatik.matcher;

import edu.kit.informatik.Average;
import edu.kit.informatik.LastValue;
import edu.kit.informatik.MinMax;
import edu.kit.informatik.State;
import edu.kit.informatik.StateTracker;
import edu.kit.informatik.Sum;
import edu.kit.informatik.Terminal;

public class AddState extends Command {

    @Override
    public void execute(StateTracker st, String input) {
        Terminal.printLine(st.addStateType(parseState(input.split(" ")[1], input.split(" ")[2])));
    }

    @Override
    public String regexMatcher() {
        return "add [a-z]+ (sum|avg|last|minmax)";
    }

    private State parseState(String name, String aggregation) {
        switch (aggregation) {
        case ("sum"):
            return new Sum(name);
        case ("avg"):
            return new Average(name);
        case ("last"):
            return new LastValue(name);
        case ("minmax"):
            return new MinMax(name);
        default:
            return null;
        }
    }
}
