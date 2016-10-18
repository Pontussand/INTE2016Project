package prompt.command;

import file_system_adapter.FileSystemAdapter;
import prompt.util.Path;

public class Ls extends Command {

    public String doCommand(Path currentDir, String input) {
        FileSystemAdapter adapter = super.getAdapter();


        String output = "";

        String[] fsoNames = adapter.ls(currentDir.getPath());

        for (String name : fsoNames) {
            output += name + "\"";
        }
        return output;



    }




}
