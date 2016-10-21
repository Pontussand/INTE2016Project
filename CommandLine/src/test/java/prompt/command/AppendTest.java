package prompt.command;

		import file_system_adapter.FakeFileSystemAdapter;
		import file_system_adapter.fake_FSO.FakeDirectory;
		import file_system_adapter.fake_FSO.FakeFSO;
		import file_system_adapter.fake_FSO.FakeFile;
		import org.junit.Before;
		import org.junit.Test;
		import prompt.CommandPrompt;
		import prompt.util.PathContainer;

		import static org.junit.Assert.assertEquals;


public class AppendTest {

	private Append append;
	private Touch touch;
	private FakeFileSystemAdapter fakeAdapter;
	private FakeDirectory root;
	private PathContainer currentDir;
	private CommandPrompt commandPrompt;
	private String expected = "before:\nHello World\nafter:\nHello World\n";

	@Before
	public void before() {

		root = new FakeDirectory("");
		fakeAdapter = new FakeFileSystemAdapter();
		fakeAdapter.setRoot(root);
		currentDir = new PathContainer("");
		append = new Append(commandPrompt);
		touch = new Touch(commandPrompt);
		append = new Append(commandPrompt = new CommandPrompt(fakeAdapter));
		FakeFile file = new FakeFile("textfile.txt", "Hello World");
		root.addFSO(file);
	}

	@Test
	public void doCommand_firstAppend() {
		expected += "Hello,";
		assertEquals(expected, append.doCommand(currentDir, "textfile.txt Hello,"));
	}

	@Test
	public void doCommand_appendToExisting() {
		expected += "I'm Dolly!";
		assertEquals(expected, append.doCommand(currentDir, "textfile.txt I'm Dolly!"));
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
