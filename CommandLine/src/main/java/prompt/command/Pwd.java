package prompt.command;

import prompt.CommandPrompt;
import prompt.util.PathContainer;

public class Pwd extends Command {

    public String doCommand(PathContainer currentDir, String input) {
       String output = currentDir.getPath();

        return output;
    }

}
