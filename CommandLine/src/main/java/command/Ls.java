package command;

import file_system_adapter.FileSystemAdapter;

public class Ls extends Command {

    public String doCommand(String currentDir, String input) {
        FileSystemAdapter adapter = super.getAdapter();

        String output = "";

        String[] fsoNames = adapter.ls(currentDir);

        for (String name : fsoNames) {
            output += name + "\n";
        }

        return output;
    }
}
