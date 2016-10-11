import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.filechooser.*;


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

    public ArrayList<String> listFoldersFromInput(String path) {

        File dir = new File(path);
        String[] files = dir.list();

        ArrayList<String> str = new ArrayList<>();

        if (files.length == 0) {
            System.out.println("The directory is empty");

        } else {

            for (String aFile : files) {
                System.out.println(aFile);
                str.add(aFile);
            }
        }
        return str;
    }




    public ArrayList<String> listFolders() {

//        my local project path
        String dirPath = "/Users/per/Dev/annika/INTE/FinalProject/testFolder";
        File dir = new File(dirPath);
        String[] files = dir.list();

        ArrayList <String> str = new ArrayList<>();

        if (files.length == 0) {
            System.out.println("The directory is empty");

        } else {

            for (String aFile : files) {
                str.add(aFile);
            }
        }
        return str;
    }

    public String findProjectDir() {
        String userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
        return userDir;
    }

    public void newDir(String dirName) {


    }
}