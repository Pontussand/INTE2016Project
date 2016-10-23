package prompt.command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFile;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;

public class CpTest {

	private Cp cp;
	private FakeFileSystemAdapter fakeAdapter;
	private FakeDirectory root;

	private PathContainer workingDir;

	@Before
	public void before() {
		root = new FakeDirectory("");
		fakeAdapter = new FakeFileSystemAdapter();
		fakeAdapter.setRoot(root);

		cp = new Cp(new CommandPrompt(fakeAdapter));
		Command.setAdapter(fakeAdapter);

		workingDir = new PathContainer("");
	}

	@Test
	public void doCommand_fileFromRootToSub(){
		FakeFile file = new FakeFile("file.txt", "");
		FakeDirectory destination = new FakeDirectory("destination");
		root.addFSO(file);
		root.addFSO(destination);

		assertEquals("", cp.doCommand(workingDir, "file.txt destination"));
		assertEquals(file, destination.getContent()[0]);
	}
}
