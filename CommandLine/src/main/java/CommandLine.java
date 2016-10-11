import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class CommandLine {


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