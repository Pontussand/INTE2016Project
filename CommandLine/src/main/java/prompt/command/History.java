package prompt.command;

import prompt.CommandPrompt;
import prompt.util.PathContainer;

public class History extends Command {

    public History(CommandPrompt cp) {
        super(cp);
    }

    public String doCommand(PathContainer currentDir, String input) {
        String result = "";
        int i = 0;
        String sep = "";
        for (String currentLine : this.ownerCommandPrompt.commandHistory) {
            result += sep + i + " " + currentLine;
            i++;
            sep = "\n";
        }
       return result;
    }
}