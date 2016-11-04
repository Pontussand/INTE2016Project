package prompt.command;

import prompt.CommandPrompt;
import prompt.util.PathContainer;

import java.util.Objects;

public class RepeatFromHistory extends Command {

    public final static String INVALID_NUMBER = " is not a number, try again!";
    public final static String OUT_OF_BOUNDS_HISTORY = "You haven't made that many commands, try again!";

    public RepeatFromHistory(CommandPrompt prompt) {
        super(prompt, "!");
    }

    public String doCommand(PathContainer currentDir, String input) {
        int index;
        try{
            index = Integer.parseInt(input);
        }catch(Exception e){
            return input + INVALID_NUMBER;
        }

        if(commandHistory.isEmpty()){
            return RepeatLast.NO_PREVIOUS_COMMAND;
        }else if(index >= commandHistory.size()){
            return OUT_OF_BOUNDS_HISTORY;
        }
        String commandToRepeat = commandHistory.get(index);
        String result = ownerCommandPrompt.command(commandToRepeat);
        return result;
    }

    @Override
    protected boolean shouldBeAddedToHistory(){
        return false;
    }

}