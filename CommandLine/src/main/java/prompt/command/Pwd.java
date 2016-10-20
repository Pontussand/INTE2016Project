package prompt.command;

import file_system_adapter.FileSystemAdapter;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

public class Pwd extends Command {

    public Pwd(CommandPrompt cp){
        super(cp);
    }

    public String doCommand(PathContainer currentDir, String input) {
        FileSystemAdapter adapter = super.getAdapter();

        String output = currentDir.getPath();

        return output;
    }

}
