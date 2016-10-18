package command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFSO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LsTest {

	private Ls ls;
	private FakeFileSystemAdapter fakeAdapter;
	private FakeDirectory root;

	@Before
	public void before() {
		ls = new Ls();
		fakeAdapter = new FakeFileSystemAdapter();
		root = new FakeDirectory("root");
		fakeAdapter.setRoot(root);
		ls.setAdapter(fakeAdapter);
	}

	@Test
	public void doCommand_correctString() {
		String currentDir = "/Folder1";
		String input = null;

		FakeDirectory fakeDir = new FakeDirectory("Folder1");

		fakeDir.addFSO(new FakeDirectory("Testfolder1"));
		fakeDir.addFSO(new FakeDirectory("Testfolder2"));

		root.addFSO(fakeDir);

		String expected = "Testfolder1\nTestfolder2\n";

		assertEquals(expected, ls.doCommand(currentDir, input));
	}

}
