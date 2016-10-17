package command;

import file_system_adapter.FileSystemAdapter;

public class Ls extends Command {

    public String doCommand(String currentDir, String input) {
        FileSystemAdapter adapter = super.getAdapter();
//        adapter.ls();
        return "";
    }




}
