package prompt;

import file_system_adapter.FakeFSAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static prompt.command.Command.commandHistory;

public class CommandPromptTest {

	private CommandPrompt prompt;
	private FakeFSAdapter fakeAdapter;
	private FakeDirectory root;

	@Before
	public void before() {
		root = new FakeDirectory("root");
		fakeAdapter = new FakeFSAdapter();
		fakeAdapter.setRoot(root);
		prompt = new CommandPrompt(fakeAdapter);
		fakeAdapter.mkdir("Folder");

		commandHistory.clear();
	}

	@Test
	public void command_invalidCommand() {
		assertEquals("love is an invalid command", prompt.command("love"));
	}

	@Test
	public void command_exit() {
		assertEquals("CommandPrompt is shutting down", prompt.command("exit"));
	}

	@Test
	public void command_append_responding() {
		assertEquals("Could not write to file :'(", prompt.command("append nonExistingFile content"));
	}

	@Test
	public void command_cd_responding() {
		FakeDirectory sub = new FakeDirectory("subfolder");
		root.addFSO(sub);
		assertEquals("", prompt.command("cd subfolder"));
	}

	@Test
	public void command_history_responding() {
		assertEquals("0 history", prompt.command("history"));
	}

	@Test
	public void command_ls_responding() {
		root.addFSO(new FakeDirectory("Testfolder1"));
		root.addFSO(new FakeDirectory("Testfolder2"));
		assertEquals("Folder\nTestfolder1\nTestfolder2\n", prompt.command("ls"));
	}

	@Test
	public void command_mkdir_responding() {
		assertEquals("", prompt.command("mkdir FolderName"));
	}

	@Test
	public void command_mkdirs_responding() {
		assertEquals("", prompt.command("mkdirs Folder1/Folder2"));
	}

	@Test
	public void command_pwd_responding() {
		prompt.setCurrentDir("Folder");
		assertEquals("Folder", prompt.command("pwd"));
	}

	@Test
	public void command_repeatFromHistory_responding() {
		commandHistory.add("history");
		assertEquals("0 history\n1 history", prompt.command("! 0"));
	}

	@Test
	public void command_repeatLast_responding() {
		commandHistory.add("history");
		assertEquals("0 history\n1 history", prompt.command("!!"));
	}

	@Test
	public void command_touch_responding() {
		assertEquals("Could not create file :'(", prompt.command("touch File"));
	}

}