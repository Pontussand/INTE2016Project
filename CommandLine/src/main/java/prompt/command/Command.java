package prompt.command;

import file_system_adapter.FileSystemAdapter;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

public abstract class Command {

    private static FileSystemAdapter adapter;

    protected CommandPrompt ownerCommandPrompt = null;

    public Command(CommandPrompt cp){
        this.ownerCommandPrompt = cp;
    }

    public static void setAdapter(FileSystemAdapter input) {
        adapter = input;
    }

    public static FileSystemAdapter getAdapter() {
        return adapter;
    }

    public abstract String doCommand(PathContainer currentDir, String input);

    public static boolean validFSOName(String name) {
        return(!name.contains(" ")
                &&!name.contains("<")
                &&!name.contains(">")
                &&!name.contains(":")
                &&!name.contains("\"") //quote
                &&!name.contains("/")
                &&!name.contains("|")
                &&!name.contains("?")
                &&!name.contains("*")
                &&!name.contains("\\") //backslash
                && (name.length() < 256));
    }

    public static boolean validFileName(String fileName) {
        return(validFSOName(fileName)
                &&fileName.endsWith(".txt"));
    }


}

