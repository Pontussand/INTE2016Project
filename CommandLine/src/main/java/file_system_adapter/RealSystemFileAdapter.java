package file_system_adapter;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

public class RealSystemFileAdapter implements FileSystemAdapter {

    @Override
    public String[] ls(String path){
        return null;
    }

    @Override
    public boolean fsoExist(String path){
        return false;
    }

    @Override
    public boolean mkdir(String path){
        return false;
    }

    @Override
    public boolean mkdirs(String path){
        return false;
    }

    @Override
    public boolean createFile(String filePath){
        return false;
    }

    @Override
    public boolean appendToFile(String filePath, String content){
        return false;
    }

    @Override
    public boolean writeToFile(String filePath, String content){
        return false;
    }

    @Override
    public boolean deleteFile(String path){
        return false;
    }

    @Override
    public boolean createDirectory(String path){
        return false;
    }

    @Override
    public boolean deleteDirectory(String path){
        return false;
    }


    /*OLD:
    @Override
    public ArrayList<String> ls(File path) {

//        

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


    @Override
    public String findProjectDir() {
        String userDir = new File(System.getProperty("user.dir")).getAbsolutePath();
        return userDir;
    }

    @Override
    public File findParentDir(File currentDir) {

        return new File(currentDir.getParent());
    }

    @Override
    public void mkdir(File newDir) {
        try {
            Files.createDirectory(newDir.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}


