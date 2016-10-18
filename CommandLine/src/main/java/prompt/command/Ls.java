package prompt.command;

import prompt.util.Path;

import file_system_adapter.FileSystemAdapter;

public class Ls extends Command {

    public String doCommand(Path currentDir, String input) {
        FileSystemAdapter adapter = super.getAdapter();

        String output = "";

        String[] fsoNames = adapter.ls(currentDir.getPath());

        for (String name : fsoNames) {
            output += name + "\n";
        }

        return output;
    }
}