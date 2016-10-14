package command;


import command.test.file_system_adapter.FileSystemAdapter;

import java.io.File;

public class Filemanager {

    private FileSystemAdapter fso;

    public Filemanager(FileSystemAdapter fso) {
        this.fso = fso;
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

    void mkdir(File newDir) {

    }


}
