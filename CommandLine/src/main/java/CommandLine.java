import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;


public class CommandLine {

    private final String userDir;
    private File currentDir;

    public CommandLine() {
        this.userDir = findProjectDir();
        this.currentDir = new File(userDir);

    }


    public ArrayList<String> listFolders(File path) {

        String[] files = path.list();

        ArrayList<String> str = new ArrayList<>();

        if(files == null) {
            System.out.println("the directory doesn't exist.");
        }else if (files.length == 0) {
            System.out.println("The directory is empty");

        } else {

            for (String aFile : files) {
                System.out.println(aFile);
                str.add(aFile);
            }
        }
        return str;
    }

    public String findProjectDir() {
        String userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
        System.out.println(userDir);
        return userDir;
    }

    public File getParentDir(File currentDir) {

        return new File(currentDir.getParent());
    }

    public void newDir(File dirLocation, String dirName) {
        File location = new File(dirLocation+"/"+dirName);
        try {
            Files.createDirectory(location.toPath());
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}