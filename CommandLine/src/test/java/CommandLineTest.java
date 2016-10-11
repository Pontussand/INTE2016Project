import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CommandLineTest {

    private  CommandLine commandLine;
    // your local project path
    private String localProjectDir = "/media/Data/Users/Felix/Documents/ubuntu GitHub/INTE2016Project/CommandLine";
    private String testFolder = localProjectDir + "/testFolder";


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

//    @Test
//    public void createDirTest() {
////    not yet implemented
//
//        String newDirName = "New Folder";
//        ArrayList <String> expectedFolders = new ArrayList<>();
//        expectedFolders.add(".DS_Store");
//        expectedFolders.add("untitled folder");
//        expectedFolders.add(newDirName);
//
//        commandLine.newDir(newDirName);
//
//        assertEquals(expectedFolders, commandLine.listFolders());
//    }

}
