
package command;

/*
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FilemanagerTest {

    // your local project path

    // the parent of your local project path
    private File localProjectDirParent = new File("/Users/per/Dev/annika/Final/");

    // your local project path HERE vv
    private File localProjectDir = new File("/Users/per/Dev/annika/Final/CommandLine");
    private File testFolder = new File(localProjectDir + "/testFolder");
    private ArrayList<String> testFolderContent;

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
    }

    @Test
    public void ls_listContent() {
        //        based on my local project path
        ArrayList<String> expectedFilesFolders = testFolderContent;
        ArrayList<String> output = filemanager.ls(testFolder);

        assertEquals(expectedFilesFolders, output);
    }

    @Test
    public void findProjectDir() {
        assertEquals(localProjectDir.toString(), filemanager.findProjectDir());
    }

    @Test
    public void getParentDir() {
        assertEquals(localProjectDirParent, filemanager.findParentDir(localProjectDir));

    }

}
*/
