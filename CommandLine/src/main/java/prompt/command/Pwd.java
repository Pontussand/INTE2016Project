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

        Pwd other = (Pwd) o;

        return Objects.equals(getName(), other.getName());
    }
}
