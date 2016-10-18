package command;

import file_system_adapter.FileSystemAdapter;

import java.io.File;

public abstract class Command {

    private static FileSystemAdapter adapter;

    public static void setAdapter(FileSystemAdapter input) {
        adapter = input;
    }

    public static FileSystemAdapter getAdapter() {
        return adapter;
    }

    public abstract String doCommand(String currentDir, String input);

}
