package prompt.command;

import file_system_adapter.FSAdapter;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import java.util.LinkedList;
import java.util.List;

public abstract class Command {

    private static FSAdapter adapter;
    public static List<String> commandHistory = new LinkedList<String>();
    public static int maxHistory = 10;
    private String name;


    protected CommandPrompt ownerCommandPrompt;

    public Command(CommandPrompt prompt, String name) {
        this.ownerCommandPrompt = prompt;
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public static void setAdapter(FSAdapter input) {
        adapter = input;
    }

    public static FSAdapter getAdapter() {
        return adapter;
    }


    protected boolean shouldBeAddedToHistory() {
        return true;
    }

    public void addToHistory(String commandUsed) {
        this.commandHistory.add(commandUsed);
        if (this.commandHistory.size() > maxHistory) {
            commandHistory.remove(0);
        }
    }

    public String execute(PathContainer currentDir, String target, String input) {
        if (shouldBeAddedToHistory()) {
            addToHistory(input);
        }
        return  doCommand(currentDir, target);
    }

    public abstract int hashCode();

    public abstract boolean equals(Object o);

    protected abstract String doCommand(PathContainer currentDir, String input);

    public static boolean validFSOName(String name) {
        return(!name.equals("")
            &&!name.contains(" ")
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

