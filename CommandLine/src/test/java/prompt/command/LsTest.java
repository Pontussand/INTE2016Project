package prompt.command;

import file_system_adapter.FakeFSAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFile;
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
	private FakeFSAdapter fakeAdapter;
	private FakeDirectory root, fakeDir1, fakeDir2;
	private PathContainer currentDir;
	private String input;

	@Before
	public void before() {
		root = new FakeDirectory("root");
		fakeAdapter = new FakeFSAdapter();
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
		fakeDir2.addFSO(new FakeDirectory("TestFolder3"));
		fakeDir2.addFSO(new FakeDirectory("TestFolder4"));
		fakeDir2.addFSO(new FakeFile("TestFile1", "tom"));
		fakeDir2.addFSO(new FakeFile("TestFile2", "tom"));

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
	public void doCommand_LsPath_Bt1() {
		String expected = "TestFile1\nTestFile2\nTestFolder1\nTestFolder2\nTestFolder3\nTestFolder4\n";
		assertEquals(expected, ls.doCommand(currentDir, "FirstFolder/SecondFolder"));
		assertEquals(expected, commandPrompt.command("ls FirstFolder/SecondFolder"));
	}

	@Test
	public void doCommand_LsAbsolutePath_Bt2(){

	}

	@Test
	public void doCommand_lsParent_Bt3(){

	}

	@Test
	public void doCommand_Ls_Bt4(){

	}

	@Test
	public void doCommand_LsDirsPath_Bt5() {
		String expected = "TestFolder1\nTestFolder2\nTestFolder3\nTestFolder4\n";
		assertEquals(expected, ls.doCommand(currentDir, "FirstFolder/SecondFolder -dirs"));
		assertEquals(expected, commandPrompt.command("ls FirstFolder/SecondFolder -dirs"));
	}

	@Test
	public void doCommand_LsDirsAbsolutePath_Bt6(){

	}

	@Test
	public void doCommand_LsDirsParent_Bt7(){

	}

	@Test
	public void doCommand_Ls_Bt8(){

	}

	@Test
	public void doCommand_LsFilesPath_Bt9() {
		String expected = "TestFile1\nTestFile2\n";
		assertEquals(expected, ls.doCommand(currentDir, "FirstFolder/SecondFolder -files"));
		assertEquals(expected, commandPrompt.command("ls FirstFolder/SecondFolder -files"));
	}



}
