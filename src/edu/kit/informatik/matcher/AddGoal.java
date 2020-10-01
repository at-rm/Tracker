package edu.kit.informatik.matcher;

import edu.kit.informatik.Between;
import edu.kit.informatik.Goal;
import edu.kit.informatik.Greater;
import edu.kit.informatik.Less;
import edu.kit.informatik.StateTracker;
import edu.kit.informatik.Terminal;

public class AddGoal extends Command {

    @Override
    public void execute(StateTracker st, String input) {
        Terminal.printLine(st.addGoal(input.split(" ")[1], parseGoal(input)));
    }

    @Override
    public String regexMatcher() {
        String less = "(less-than " + super.doubleRegex() + ")";
        String greater = "(greater-than " + super.doubleRegex() + ")";
        String between = "(between " + super.doubleRegex() + " and " + super.doubleRegex() + ")";
        return "goal [a-z]+ " + "(" + less + "|" + greater + "|" + between + ")";
    }

    private Goal parseGoal(String s) {
        if (s.matches("goal [a-z]+ less-than " + super.doubleRegex())) {
            return new Less(Double.parseDouble(s.split("less-than ")[1]));
        }
        if (s.matches("goal [a-z]+ greater-than " + super.doubleRegex())) {
            return new Greater(Double.parseDouble(s.split("greater-than ")[1]));
        }
        if (s.matches("goal [a-z]+ between " + super.doubleRegex() + " and " + super.doubleRegex())) {
            return new Between(Double.parseDouble(s.split("between ")[1].split(" and ")[0]),
                    Double.parseDouble(s.split("between ")[1].split(" and ")[1]));
        }
        return null;
    }
}
