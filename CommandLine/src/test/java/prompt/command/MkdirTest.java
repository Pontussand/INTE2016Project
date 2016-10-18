package prompt.command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFSO;
import org.junit.Before;
import org.junit.Test;
import prompt.util.Path;

import static org.junit.Assert.assertEquals;


public class MkdirTest {

	private Mkdir mkdir;
	private FakeFileSystemAdapter fakeAdapter;
	private FakeDirectory root;
	private Path currentDir;
	private String input;

	@Before
	public void before() {
		mkdir = new Mkdir();
		fakeAdapter = new FakeFileSystemAdapter();
		root = new FakeDirectory("");
		fakeAdapter.setRoot(root);
		mkdir.setAdapter(fakeAdapter);
		currentDir = new Path("");

		input = "";
	}

	@Test
	public void doCommand_newDir() {
		String folderName = "new folder";
		String result = mkdir.doCommand(currentDir, folderName);
		assertEquals("", result);

		FakeFSO[] rootContent = root.getContent();
		assertEquals(1, rootContent.length);

		assertEquals("", result);


//		assertEquals(new FakeDirectory(folderName), rootContent[0].getName());
	}




}
