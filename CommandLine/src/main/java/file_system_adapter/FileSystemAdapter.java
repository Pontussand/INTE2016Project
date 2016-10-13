package file_system_adapter;

import java.io.File;
import java.util.ArrayList;

public interface FileSystemAdapter {

    ArrayList<String> ls(File path);

    String findProjectDir();

    //    returvärde behöver ändras
    File findParentDir(File currentDir);

    void mkdir(File newDir);
}


//    public boolean createFile(String path);
//    public boolean deleteFile(String path);
//    public boolean createDirectory(String path);
//    public boolean deleteDirectory(String path);