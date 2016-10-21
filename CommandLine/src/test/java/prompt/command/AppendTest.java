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
	private static final String UNABLE_TO_APPEND = "Could not write to file :'(";

	@Before
	public void before() {

		root = new FakeDirectory("");
		fakeAdapter = new FakeFileSystemAdapter();
		fakeAdapter.setRoot(root);

		commandPrompt = new CommandPrompt(fakeAdapter);

		currentDir = new PathContainer("");
		append = new Append(commandPrompt);
		append.setAdapter(fakeAdapter);
		touch = new Touch(commandPrompt);
		touch.setAdapter(fakeAdapter);

		commandPrompt.command("touch textfile.txt");
	}

	@Test
	public void ommand_firstAppend() {
		assertEquals("", commandPrompt.command("append textfile.txt Hello,"));
	}

	@Test
	public void command_appendToExisting() {
		commandPrompt.command("append textfile.txt Hello,");
		assertEquals("", commandPrompt.command("append textfile.txt I'm Dolly!"));
	}

	@Test
	public void command_append_fail() {
		assertEquals(UNABLE_TO_APPEND, commandPrompt.command("append yoyo"));
	}

	@Test
	public void command_append_nonExistingFile() {
		assertEquals(UNABLE_TO_APPEND, commandPrompt.command("append myFile.txt Blablabla"));
	}

}
