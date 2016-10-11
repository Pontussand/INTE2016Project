import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

public class CommandLine {

    public CommandLine() {
//        this.commandPrompt();
    }

//    private void commandPrompt() {
//        System.out.println("> ");
//
//        Scanner keyboard = new Scanner(System.in);
//        String input = keyboard.nextLine();
//
//
//    }

    public String listFoldersFromInput(String path) {

        File dir = new File(path);
        String[] files = dir.list();

        String str = "";

        if (files.length == 0) {
            System.out.println("The directory is empty");

        } else {

            for (String aFile : files) {
                System.out.println(aFile);
                str += aFile;
            }
        }

        return str;
    }




    public String listFolders() {

//        local files on annikas mac
        String dirPath = "/Users/per/Dev/annika/INTE/FinalProject/testFolder";
        File dir = new File(dirPath);
        String[] files = dir.list();

        String str = "";

        if (files.length == 0) {
            System.out.println("The directory is empty");

        } else {

            for (String aFile : files) {
                System.out.println(aFile);
                str += aFile;
            }
        }

        return str;
    }
}