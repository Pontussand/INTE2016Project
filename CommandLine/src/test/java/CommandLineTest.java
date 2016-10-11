import org.junit.*;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CommandLineTest {

    // your local project path

    // the parent of your local project path
    private File localProjectDirParent = new File("/media/Data/Users/Felix/Documents/ubuntu GitHub/INTE2016Project");

    // your local project path HERE vv
    private File localProjectDir = new File("/media/Data/Users/Felix/Documents/ubuntu GitHub/INTE2016Project/CommandLine");
    private File testFolder = new File(localProjectDir + "/testFolder");
    private ArrayList<String> testFolderContent;

    private CommandLine commandLine;


    @Before
    public void setup() {
        commandLine = new CommandLine();

        testFolderContent = new ArrayList<>();
        testFolderContent.add("file1.txt");
        testFolderContent.add("file2.txt");
        testFolderContent.add("Folder1");
        testFolderContent.add("Folder2");
    }

    @Test
    public void listTest() {
        //        based on my local project path
        ArrayList<String> expectedFilesFolders = testFolderContent;

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

    @Test
    public void createDirTest() {
        String newDirName = "New Folder";
        ArrayList <String> expectedFolders = testFolderContent;
        expectedFolders.add(newDirName);

        commandLine.newDir(testFolder, newDirName);

        assertEquals(expectedFolders, commandLine.listFolders(testFolder));
    }

}
