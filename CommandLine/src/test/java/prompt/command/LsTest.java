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
	private FakeFileSystemAdapter fakeAdapter;
	private FakeDirectory root;
	private PathContainer currentDir;
	private String input;

	@Before
	public void before() {
		root = new FakeDirectory("root");
		fakeAdapter = new FakeFileSystemAdapter();
		fakeAdapter.setRoot(root);
		ls.setAdapter(fakeAdapter);
		currentDir = new PathContainer("/Folder1");
		input = null;

		ls = new Ls();
	}

	@Test
	public void doCommand_correctString() {
		FakeDirectory fakeDir = new FakeDirectory("Folder1");
		fakeDir.addFSO(new FakeDirectory("Testfolder1"));
		fakeDir.addFSO(new FakeDirectory("Testfolder2"));

		root.addFSO(fakeDir);
		String expected = "Testfolder1\nTestfolder2\n";

		assertEquals(expected, ls.doCommand(currentDir, input));
	}

	@Test
	public void doCommand_correctStringFromAltPath() {
		FakeDirectory fakeDir = new FakeDirectory("Folder1");
		FakeDirectory fakeDir2 = new FakeDirectory("Testfolder1");
		FakeDirectory fakeDir3 = new FakeDirectory("folder");

		fakeDir2.addFSO(fakeDir3);
		fakeDir.addFSO(fakeDir2);
		root.addFSO(fakeDir);

		String expected = "folder\n";

		currentDir.append("/Testfolder1");

		assertEquals(expected, ls.doCommand(currentDir, input));
	}



}
