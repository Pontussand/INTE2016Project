package command;


import command.file_system_adapter.FileSystemAdapter;

import java.io.File;

public class Filemanager { //fix name FileManager

//   private FileSystemAdapter fso;

//    public Filemanager(FileSystemAdapter fso) {
//        this.fso = fso;
//    }

    public boolean correctFileName(String fileName) {
        return(!fileName.contains(" ")
                &&fileName.endsWith(".txt")
                &&!fileName.contains("<")
                &&!fileName.contains(">")
                &&!fileName.contains(":")
                &&!fileName.contains("\"")
                &&!fileName.contains("/")
                &&!fileName.contains("\\")
                &&!fileName.contains("|")
                &&!fileName.contains("?")
                &&!fileName.contains("*")
                && (fileName.length() < 256));
    }

    public boolean fileExists(String fileName){
        return false;
    }
    public boolean correctDirectoryName(String directoryName){
        return false;
    }
    public boolean directoryExists(String directoryName){
        return false;
    }

// command.test ok filnamn
// command.test fil finns eller ej
// command.test ok path
// command.test path finns eller ej

    public void ls(File path) {

     //   fso.ls(path);

    }

    void findProjectDir() {

    }

    void findParentDir(File currentDir) {

    }

    void mkdir(File newDir) {

    }

}
