package prompt.command;

import prompt.CommandPrompt;
import prompt.util.PathContainer;

public class History extends Command {

    public String doCommand(PathContainer currentDir, String input) {
        String result = "";
        int i = 0;
        String sep = "";
        for (String currentLine : commandHistory) {
            result += sep + i + " " + currentLine;
            i++;
            sep = "\n";
        }
       return result;
    }
}