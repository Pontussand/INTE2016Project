package prompt.command;

import file_system_adapter.FakeFSAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFile;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class AppendTest {

	private Append append;
	private FakeFSAdapter fakeAdapter;
	private FakeDirectory root;
	private PathContainer currentDir;
	private CommandPrompt commandPrompt;
	private FakeFile testFile;

	@Before
	public void before() {

		root = new FakeDirectory("");
		fakeAdapter = new FakeFSAdapter();
		fakeAdapter.setRoot(root);
		currentDir = new PathContainer("");
		commandPrompt = new CommandPrompt(fakeAdapter);
		append = new Append(commandPrompt);
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

	@Test
	public void equals_Symmetric() {
		Append append1 = new Append(commandPrompt);
		Append append2 = new Append(commandPrompt);

		assertTrue(append1.equals(append2) && append2.equals(append1));
	}

	@Test
	public void hashCode_Symetric() {
		Append append1 = new Append(commandPrompt);
		Append append2 = new Append(commandPrompt);

		assertTrue(append1.hashCode() == append2.hashCode());
	}
}
