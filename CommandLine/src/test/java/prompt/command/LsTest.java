package prompt.command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;


public class LsTest {

	private Ls ls;
	private Mkdir mkdir;
	private Touch touch;
	private CommandPrompt commandPrompt;
	private FakeFileSystemAdapter fakeAdapter;
	private FakeDirectory root, fakeDir1, fakeDir2;
	private PathContainer currentDir;
	private String input;

	@Before
	public void before() {
		root = new FakeDirectory("root");
		fakeAdapter = new FakeFileSystemAdapter();
		fakeAdapter.setRoot(root);
		currentDir = new PathContainer("");
		input = "";

		commandPrompt = new CommandPrompt(fakeAdapter);
		ls = new Ls(commandPrompt);
		ls.setAdapter(fakeAdapter);
		mkdir = new Mkdir(commandPrompt);
		mkdir.setAdapter(fakeAdapter);
		touch = new Touch(commandPrompt);
		touch.setAdapter(fakeAdapter);

		fakeDir1 = new FakeDirectory("FirstFolder");

		fakeDir2 = new FakeDirectory("SecondFolder");
		fakeDir2.addFSO(new FakeDirectory("TestFolder1"));
		fakeDir2.addFSO(new FakeDirectory("TestFolder2"));

		fakeDir1.addFSO(fakeDir2);

		root.addFSO(fakeDir1);
	}
//
//	@Test
//	public void command_listingInRoot() {
//		String expected = "FirstFolder\n";
//		assertEquals(expected, commandPrompt.command("ls"));
//	}
//
//	@Test
//	public void doCommand_listingInRoot() {
//		String expected = "FirstFolder\n";
//		assertEquals(expected, ls.doCommand(currentDir, input));
//	}




	@Test
	public void doCommand_listingInFolder() {
		String expected = "TestFolder1\nTestFolder2\n";
		assertEquals(expected, ls.doCommand(currentDir, "FirstFolder/SecondFolder"));
		assertEquals(expected, commandPrompt.command("ls FirstFolder/SecondFolder"));
	}



}
