package prompt.command;

import prompt.CommandPrompt;
import prompt.util.PathContainer;

public class RepeatLast extends Command {

    public final static String NO_PREVIOUS_COMMAND = "Sorry there are no previous commands";

    public RepeatLast(CommandPrompt prompt) {
        super(prompt);
    }

    public String getName(){
        return "!!";
    }

    public String doCommand(PathContainer currentDir, String input) {
        if(commandHistory.isEmpty()){
            return NO_PREVIOUS_COMMAND;
        }
        String lastCommand = commandHistory.get(commandHistory.size()-1);
        String result = ownerCommandPrompt.command(lastCommand);
        return result;
    }

    @Override
    protected boolean shouldBeAddedToHistory(){
        return false;
    }
}