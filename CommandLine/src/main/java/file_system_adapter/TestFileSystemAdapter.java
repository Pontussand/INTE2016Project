package file_system_adapter;

import java.io.File;
import java.util.ArrayList;

public class TestFileSystemAdapter implements FileSystemAdapter {

    @Override
    public ArrayList<String> ls(File path) {
        return new ArrayList<String>();
    }


    @Override
    public String findProjectDir() {
        return new String;
    }


//    returvärde behöver ändras?
    @Override
    public File findParentDir(File currentDir) {
        return new File("");
    }

    @Override
    public void mkdir(File newDir) {

    }

}
