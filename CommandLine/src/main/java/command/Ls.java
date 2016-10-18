package command;

import file_system_adapter.FileSystemAdapter;

public class Ls extends Command {

    public String doCommand(String currentDir, String input) {
        FileSystemAdapter adapter = super.getAdapter();
        System.out.println(adapter);

        String output = "";

        String[] fsoNames = adapter.ls(currentDir);

        for (String name : fsoNames) {
            output += name + "\n";
        }

        System.out.println(output);

        return output;
    }




}
