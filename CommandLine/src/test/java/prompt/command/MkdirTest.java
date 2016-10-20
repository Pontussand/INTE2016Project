package prompt.command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFSO;
import org.junit.Before;
import org.junit.Test;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;


public class MkdirTest {

	private Mkdir mkdir;
	private FakeFileSystemAdapter fakeAdapter;
	private FakeDirectory root;
	private PathContainer currentDir;

	@Before
	public void before() {
		mkdir = new Mkdir();
		fakeAdapter = new FakeFileSystemAdapter();
		root = new FakeDirectory("");
		fakeAdapter.setRoot(root);
		mkdir.setAdapter(fakeAdapter);
		currentDir = new PathContainer("");
	}

	@Test
	public void doCommand_newDir() {
		String folderName = "new folder";
		FakeDirectory expectedDir = new FakeDirectory(folderName);

		String result = mkdir.doCommand(currentDir, folderName);
		assertEquals("", result);

		FakeFSO[] rootContent = root.getContent();
		assertEquals(1, rootContent.length);
		assertEquals(expectedDir, rootContent[0]);
	}

	@Test
	public void doCommand_newDirAlreadyExisting() {
		String existingFolder = "java";
		assertEquals("", (mkdir.doCommand(currentDir, existingFolder)));

		String newFolder = "java";
		assertEquals(Mkdir.ERROR_MSG, mkdir.doCommand(currentDir, newFolder));
	}
}
