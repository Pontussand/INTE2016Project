package prompt.command;

import prompt.CommandPrompt;
import prompt.util.PathContainer;

import java.util.Objects;

public class Pwd extends Command {

    public Pwd(CommandPrompt prompt){
        super(prompt, "pwd");
    }

    public String doCommand(PathContainer currentDir, String input) {
       String output = currentDir.getPath();

        return output;
    }

}
