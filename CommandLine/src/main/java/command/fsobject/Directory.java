package command.fsobject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Directory extends Fso {

    public Directory() {
        createPath();

    }

    private void createPath() {
        Path path = Paths.get("C:\\Directory2\\Sub2\\Sub-Sub2");
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
                System.out.println(path.toString());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




}
