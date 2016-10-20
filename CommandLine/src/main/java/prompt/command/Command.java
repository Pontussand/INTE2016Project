package prompt.command;

import file_system_adapter.FileSystemAdapter;
import prompt.util.PathContainer;

public abstract class Command {

    private static FileSystemAdapter adapter;

    public static void setAdapter(FileSystemAdapter input) {
        adapter = input;
    }

    public static FileSystemAdapter getAdapter() {
        return adapter;
    }

    public abstract String doCommand(PathContainer currentDir, String input);

}

