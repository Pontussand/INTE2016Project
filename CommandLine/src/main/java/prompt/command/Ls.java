package prompt.command;

import prompt.util.Path;

import file_system_adapter.FileSystemAdapter;

public class Ls extends Command {

    public String doCommand(Path currentDir, String input) {
        FileSystemAdapter adapter = super.getAdapter();

        String output = "";

        String path = currentDir.getPath();

        String[] fsoNames = adapter.ls(path);

        for (String name : fsoNames) {
            output += name + "\n";
        }

        return output;
    }
}