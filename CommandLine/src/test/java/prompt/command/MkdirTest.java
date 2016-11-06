package prompt.command;

import file_system_adapter.FakeFSAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFSO;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class MkdirTest {

	private Mkdir mkdir, mkdir2;
	private Pwd pwd;
	private FakeFSAdapter fakeAdapter;
	private FakeDirectory root;
	private PathContainer currentDir;
	private CommandPrompt commandPrompt;

	@Before
	public void before() {
		root = new FakeDirectory("");
		fakeAdapter = new FakeFSAdapter();
		fakeAdapter.setRoot(root);
		mkdir.setAdapter(fakeAdapter);

		mkdir2 = new Mkdir(commandPrompt);
		pwd = new Pwd(commandPrompt);
		currentDir = new PathContainer("");

		commandPrompt = new CommandPrompt(fakeAdapter);
		mkdir = new Mkdir(commandPrompt);
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
	public void doCommand_newDir2() {
		FakeDirectory expectedDir = new FakeDirectory("NewFolder");

		assertEquals("", mkdir.doCommand(currentDir, "/NewFolder"));

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

	@Test
	public void equals_itself() {
		assertTrue(mkdir.equals(mkdir));
	}

	@Test
	public void equals_null() {
		assertFalse(mkdir.equals(null));
	}

	@Test
	public void equals_symetric() {
		assertTrue(mkdir.equals(mkdir2) && mkdir2.equals(mkdir));
	}

	@Test
	public void hashCode_symetric() {
		assertTrue(mkdir.hashCode() == mkdir2.hashCode());
	}

	@Test
	public void equals_unsymetric() {
		assertFalse(pwd.equals(mkdir) && mkdir.equals(pwd));
	}

	@Test
	public void hashCode_unsymetric() {
		assertFalse(pwd.hashCode() == mkdir.hashCode());
	}
}
