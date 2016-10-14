
package command;

/*
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CreateAndRemove {

    // your local project path

    // the parent of your local project path
    private File localProjectDirParent = new File("/Users/per/Dev/annika/Final/");

    // your local project path HERE vv
    private File localProjectDir = new File("/Users/per/Dev/annika/Final/CommandLine");
    private File testFolder = new File(localProjectDir + "/testFolder");
    private ArrayList<String> testFolderContent;

    private File newDir, removeDir;
    private String newDirName;
    ArrayList<String> expectedFolders;


    private Filemanager filemanager;


    @Before
    public void setup() {
        filemanager = new Filemanager();

        testFolderContent = new ArrayList<>();
        testFolderContent.add(".DS_Store");
        testFolderContent.add("file1.txt");
        testFolderContent.add("file2.txt");
        testFolderContent.add("Folder1");
        testFolderContent.add("Folder2");

        expectedFolders = new ArrayList<>(testFolderContent);
        newDirName = "New Folder";
        newDir = new File(testFolder + "/" + newDirName);
    }


    @Test
    public void mkdir_newDir() {
        expectedFolders.add(newDirName);
        filemanager.mkdir(newDir);

        assertEquals(expectedFolders, filemanager.ls(testFolder));
    }


    @Test
    public void rmdir_removeEmpty() {
        filemanager.rmdir(newDir);
        assertEquals(testFolderContent, filemanager.ls(testFolder));
    }


}
*/
