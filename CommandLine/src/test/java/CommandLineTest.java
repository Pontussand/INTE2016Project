import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CommandLineTest {

    private  CommandLine commandLine;


    @Before
    public void setup() {
        commandLine = new CommandLine();

    }

    @Test
    public void listTest() {
        ArrayList<String> expectedFolders = new ArrayList<>();
        expectedFolders.add(".DS_Store");
        expectedFolders.add("untitled folder");

        assertEquals(expectedFolders, commandLine.listFolders());
    }

    @Test
    public void listFromInputTest() {
        String userInput = "/Users/per/Dev/annika/INTE/FinalProject/testFolder";
        ArrayList<String> expectedString = new ArrayList<>();
        expectedString.add(".DS_Store");
        expectedString.add("untitled folder");

        assertEquals(commandLine.listFoldersFromInput(userInput), expectedString);
    }

//    @Test
//    public void createDirTest() {
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
