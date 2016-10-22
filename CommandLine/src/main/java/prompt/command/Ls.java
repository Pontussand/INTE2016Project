package prompt.command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.RealSystemFileAdapter;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import file_system_adapter.FileSystemAdapter;

public class Ls extends Command {

    public Ls(CommandPrompt cp){
        super(cp);
    }

    public String getName(){
        return "ls";
    }

    public String doCommand(PathContainer currentDir, String input) {
        FileSystemAdapter adapter = super.getAdapter();
        String currDir = currentDir.getPath();
        String fullPath;
        String output = "";

        if (input != null && input.length() > 0) {
            fullPath = PathContainer.getFullPath(currDir, input);
        } else {
            fullPath = currDir;
        }

        String[] fsoNames = adapter.ls(fullPath);
        for (String name : fsoNames) {
            output += name + "\n";
        }
        return output;
    }
}