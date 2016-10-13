package command;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;


public class Filemanager {

    private final String userDir;
    private File currentDir;

    public Filemanager() {
        this.userDir = findProjectDir();
        this.currentDir = new File(userDir);

    }


    public ArrayList<String> ls(File path) {

        String[] files = path.list();

        ArrayList<String> strings = new ArrayList<>();

        if (files == null) {
            System.out.println("the directory doesn't exist.");
        } else if (files.length == 0) {
            System.out.println("The directory is empty");
        } else {

            for (String aFile : files) {
                System.out.println(aFile);
                strings.add(aFile);
            }
        }

        return strings;
    }

    public String findProjectDir() {
        String userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
//		System.out.println(userDir);
        return userDir;
    }

    public File findParentDir(File currentDir) {

        return new File(currentDir.getParent());
    }

    public void mkdir(File newDir) {
        try {
            Files.createDirectory(newDir.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void rmdir(File file) {
        try {
            Files.delete(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}