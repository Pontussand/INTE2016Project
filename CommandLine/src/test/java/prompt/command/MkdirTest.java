package prompt.command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFSO;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;


public class MkdirTest {

	private Mkdir mkdir;
	private FakeFileSystemAdapter fakeAdapter;
	private FakeDirectory root;
	private PathContainer currentDir;

	@Before
	public void before() {
		root = new FakeDirectory("");
		fakeAdapter = new FakeFileSystemAdapter();
		fakeAdapter.setRoot(root);
		mkdir.setAdapter(fakeAdapter);
		currentDir = new PathContainer("");

		mkdir = new Mkdir(new CommandPrompt(fakeAdapter));
	}

	@Test
	public void doCommand_newDir() {
		FakeDirectory expectedDir = new FakeDirectory("NewFolder");

		assertEquals("", mkdir.doCommand(currentDir, "NewFolder"));

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

	@Test
	public void doCommand_invalidName() {
		assertEquals(Mkdir.ERROR_MSG, mkdir.doCommand(currentDir, ""));
		assertEquals(Mkdir.ERROR_MSG, mkdir.doCommand(currentDir, ""));
		assertEquals(Mkdir.ERROR_MSG, mkdir.doCommand(currentDir, "<"));
		assertEquals(Mkdir.ERROR_MSG, mkdir.doCommand(currentDir, ">"));
		assertEquals(Mkdir.ERROR_MSG, mkdir.doCommand(currentDir, ":"));
		assertEquals(Mkdir.ERROR_MSG, mkdir.doCommand(currentDir, "\""));
		assertEquals(Mkdir.ERROR_MSG, mkdir.doCommand(currentDir, "/"));
		assertEquals(Mkdir.ERROR_MSG, mkdir.doCommand(currentDir, "|"));
		assertEquals(Mkdir.ERROR_MSG, mkdir.doCommand(currentDir, "?"));
		assertEquals(Mkdir.ERROR_MSG, mkdir.doCommand(currentDir, "*"));
		assertEquals(Mkdir.ERROR_MSG, mkdir.doCommand(currentDir, "\\"));
		assertEquals(Mkdir.ERROR_MSG, mkdir.doCommand(currentDir, "<"));
		assertEquals(Mkdir.ERROR_MSG, mkdir.doCommand(currentDir, amountCharacterString(256)));
		assertEquals(256, amountCharacterString(256).length());
	}

	private String amountCharacterString(int amount){
		String ret = "";
		for(int i = 0; i < amount; i++){
			ret += "a";
		}
		return ret;
	}
}
