package command;


import command.file_system_adapter.FileSystemAdapter;

import java.io.File;

public class Filemanager {

    private FileSystemAdapter fileSystemAdapter;

    public Filemanager(FileSystemAdapter fileSystemAdapter) {
        this.fileSystemAdapter = fileSystemAdapter;
    }


// command.test ok filnamn
// command.test fil finns eller ej
// command.test ok path
// command.test path finns eller ej

//    public void ls(File path) {
//
//        fso.ls(path);
//
//    }

    void findProjectDir() {

    }

    void findParentDir(File currentDir) {

    }

    void mkdir(String path) {
        this.fileSystemAdapter.createDirectory(path);
    }
}
