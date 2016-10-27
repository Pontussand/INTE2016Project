package prompt.command;

import prompt.CommandPrompt;
import prompt.util.PathContainer;

import java.util.Objects;

public class History extends Command {

    public History(CommandPrompt prompt) {
        super(prompt, "history");
    }

    public int hashCode() {
        return Objects.hash(getName());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }

        History other = (History) o;

        return Objects.equals(getName(), other.getName());
    }

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