package fsobject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class File extends Fso {

    public File() {


    }

    private void createPath() {
        Path path = Paths.get("C:\\Directory2\\Sub2\\Sub-Sub2");
        //if directory exists?
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println(path.toString());

            } catch (IOException e) {
                //fail to create directory
                e.printStackTrace();
            }
        }

    }




//    private void createFile() {
//        new File("C:\\Directory1").mkdir();
//        new File("C:\\Directory2\\Sub2\\Sub-Sub2").mkdirs();
//
//        Path path = Paths.get("C:\\Directory1");
//        Files.createDirectories(path);
//    }

//    private void createWithNio() {
//        Path path = Paths.get("C:\\Directory1");
//        Files.createDirectories(path);
//    }






}
