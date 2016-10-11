import org.junit.*;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CommandLineTest {

    // your local project path
    private File localProjectDir = new File("/Users/per/Dev/annika/INTE/FinalProject/CommandLine");

    // the parent of your local project path
    private File localProjectDirParent = new File("/Users/per/Dev/annika/INTE/FinalProject");
    private String testFolder = localProjectDir + "/testFolder";

    CommandLine commandLine;


    @Before
    public void setup() {
        commandLine = new CommandLine();
    }

    @Test
    public void listTest() {
        //        based on my local project path
        ArrayList<String> expectedFilesFolders = new ArrayList<>();
        expectedFilesFolders.add("file1.txt");
        expectedFilesFolders.add("file2.txt");
        expectedFilesFolders.add("Folder1");
        expectedFilesFolders.add("Folder2");

        ArrayList<String> output = commandLine.listFolders(testFolder);
        System.out.println(output);

        assertEquals(expectedFilesFolders, output);
    }

    @Test
    public void findProjectDir() {
        assertEquals(localProjectDir, commandLine.findProjectDir());
    }

    @Test
    public void getParentDir() {
        assertEquals(localProjectDirParent, commandLine.getParentDir(localProjectDir));
    }

}
