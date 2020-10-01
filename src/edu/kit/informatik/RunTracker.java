package edu.kit.informatik;

import edu.kit.informatik.matcher.*;

public class RunTracker {

    private StateTracker st = new StateTracker();
    private Command[] commands = new Command[] {new AddState(), new AddGoal(), new RemoveGoal(), new Goals(),
            new Record(), new StatePrinter(), new Progress(), new Top(), new GoalsWeek(), new NextDay(), new SetDay(),
            new Quit(), new SlidingWindow(), new Normalize(), new Peaks(), new PulseData()};

    /**
     * Starts the RunTracker.
     */
    public RunTracker() {
        //
    }

    /**
     * Runs the program. Accepts user input and then resolves it to a command.
     */
    public void runTracker() {
        while (true) {
            String s = Terminal.readLine();
            boolean matched = false;
            for (int i = 0; i < commands.length; i++) {
                if (s.matches(commands[i].regexMatcher())) {
                    commands[i].execute(st, s);
                    if (i == 11) {
                        return;
                    }
                    matched = true;
                    break;
                }
            }
            if (!matched) {
                Terminal.printError("not a valid command.");
            }
        }
    }
}
