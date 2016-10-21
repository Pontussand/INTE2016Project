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
        String output = "";
        String path;

        if (adapter instanceof FakeFileSystemAdapter) {
            input = "/" + input;
        }

        if (input.length() > 0 && adapter.isDir(input)) {
            PathContainer newPathContainer = new PathContainer(input);
            path = newPathContainer.getPath();

        } else {
            path = currentDir.getPath();
        }

        String[] fsoNames = adapter.ls(path);
        for (String name : fsoNames) {
            output += name + "\n";
        }
        return output;
    }
}