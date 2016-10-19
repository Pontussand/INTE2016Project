package command;

import file_system_adapter.FileSystemAdapter;

import java.io.File;

public abstract class Command {

    private static FileSystemAdapter adapter;

    protected static void setAdapter(FileSystemAdapter adapter) {
        adapter = adapter;
    }

    public FileSystemAdapter getAdapter() {
        return adapter;
    }

    public abstract String doCommand(String currentDir, String input);

    public boolean correctFileName(String fileName) {
        return(!fileName.contains(" ")
                &&fileName.endsWith(".txt")
                &&!fileName.contains("<")
                &&!fileName.contains(">")
                &&!fileName.contains(":")
                &&!fileName.contains("\"")
                &&!fileName.contains("/")
                &&!fileName.contains("\\")
                &&!fileName.contains("|")
                &&!fileName.contains("?")
                &&!fileName.contains("*")
                && (fileName.length() < 256));
    }

    public boolean fileExists(String fileName){

        return false;
    }
    public boolean correctDirectoryName(String directoryName){
        return(!directoryName.contains(" ")
                && directoryName.startsWith("\\")
                &&!directoryName.contains("<")
                &&!directoryName.contains(">")
                &&!directoryName.contains(":")
                &&!directoryName.contains("\"")
                &&!directoryName.contains("/")
                &&!directoryName.contains("|")
                &&!directoryName.contains("?")
                &&!directoryName.contains("*")
                && (directoryName.length() < 256));
    }
    public boolean directoryExists(String directoryName){
        return false;
    }

}
