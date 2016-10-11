import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CommandLineTest {

    private  CommandLine commandLine;
    // your local project path HERE vv
    private String localProjectDir = "/media/Data/Users/Felix/Documents/ubuntu GitHub/INTE2016Project/CommandLine";
    private String testFolder = localProjectDir + "/testFolder";
    private ArrayList<String> testFolderContent;


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
    public void createDirTest() {
        String newDirName = "New Folder";
        ArrayList <String> expectedFolders = testFolderContent;
        expectedFolders.add(newDirName);

        commandLine.newDir(testFolder, newDirName);

        assertEquals(expectedFolders, commandLine.listFolders(testFolder));
    }

}
