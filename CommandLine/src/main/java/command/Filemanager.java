package command;


import file_system_adapter.FileSystemAdapter;

import java.io.File;
import java.util.ArrayList;

public class Filemanager {

    private FileSystemAdapter fso;

    public Filemanager(FileSystemAdapter fso) {
        this.fso = fso;
    }






    public void ls(File path) {

        fso.ls(path);

    }

    void findProjectDir() {

    }

    void findParentDir(File currentDir) {

    }

    void mkdir(File newDir) {

    }


}
