package prompt.command;

import file_system_adapter.FileSystemAdapter;
import prompt.util.PathContainer;

public class Pwd extends Command {

    public String doCommand(PathContainer currentDir, String input) {
        FileSystemAdapter adapter = super.getAdapter();

        String output = currentDir.getPath();

        return output;
    }

}
