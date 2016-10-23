package prompt.command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFile;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;


public class AppendTest {

	private Append append;
	private FakeFileSystemAdapter fakeAdapter;
	private FakeDirectory root;
	private PathContainer currentDir;

	private FakeFile testFile;

	@Before
	public void before() {

		root = new FakeDirectory("");
		fakeAdapter = new FakeFileSystemAdapter();
		fakeAdapter.setRoot(root);
		currentDir = new PathContainer("");

		append = new Append(new CommandPrompt(fakeAdapter));
		append.setAdapter(fakeAdapter);

		testFile = new FakeFile("textfile.txt", "");
		root.addFSO(testFile);
	}

	@Test
	public void doCommand_firstAppend() {
		String expected = "Hello, World";
		assertEquals("", append.doCommand(currentDir, "textfile.txt Hello, World"));
		assertEquals(expected, testFile.getContent());
	}

	@Test
	public void doCommand_appendToExisting() {
		testFile.setContent("I'm ");
		String expected = "I'm \nDolly!";

		assertEquals("", append.doCommand(currentDir, "textfile.txt Dolly!"));
		assertEquals(expected, testFile.getContent());
	}

	@Test
	public void doCommand_appendFail() {
		assertEquals(Append.UNABLE_TO_APPEND, append.doCommand(currentDir, "yoyo"));
	}

	@Test
	public void doCommand_nonExistingFile() {
		assertEquals(Append.UNABLE_TO_APPEND, append.doCommand(currentDir, "myFile.txt Blablabla"));
	}

}
