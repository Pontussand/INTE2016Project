package command;

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

		assertEquals(expectedFilesFolders, output);
	}

	@Test
	public void findProjectDir() {
		assertEquals(localProjectDir.toString(), commandLine.findProjectDir());
	}

	@Test
	public void getParentDir() {
		assertEquals(localProjectDirParent, commandLine.getParentDir(localProjectDir));

	}

	@Test
	public void createRemoveDirTest() {
		String newDirName = "New Folder";
		File newDir = new File(testFolder + "/" + newDirName);
		ArrayList<String> expectedFolders = new ArrayList<>(testFolderContent);
		expectedFolders.add(newDirName);

		commandLine.newDir(newDir);
		assertEquals(expectedFolders, commandLine.listFolders(testFolder));

		commandLine.removeDirOrFile(newDir);
		assertEquals(testFolderContent, commandLine.listFolders(testFolder));
	}

}
