package prompt.command;

import file_system_adapter.FakeFSAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFile;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CpTest {

	private Cp cp;
	private FakeFSAdapter fakeAdapter;
	private FakeDirectory root;
	private CommandPrompt commandPrompt;

	private PathContainer workingDir;

	@Before
	public void before() {
		root = new FakeDirectory("");
		fakeAdapter = new FakeFSAdapter();
		fakeAdapter.setRoot(root);
		commandPrompt = new CommandPrompt(fakeAdapter);
		cp = new Cp(commandPrompt);
		Command.setAdapter(fakeAdapter);

		workingDir = new PathContainer("");
	}

	@Test
	public void doCommand_fileFromRootToSub() {
		FakeFile file = new FakeFile("file.txt", "");
		FakeDirectory destination = new FakeDirectory("destination");
		root.addFSO(file);
		root.addFSO(destination);

		assertEquals("", cp.doCommand(workingDir, "file.txt destination"));
		assertEquals(file, destination.getContent()[0]);
	}

	@Test
	public void doCommand_fileFromRootToNonexistentSub() {
		FakeFile file = new FakeFile("file.txt", "");
		root.addFSO(file);
		assertEquals(cp.COULDNT_COPY, cp.doCommand(workingDir, "file.txt destination"));
	}

	@Test
	public void doCommand_emptyDirFromRootToSub() {
		FakeDirectory dir = new FakeDirectory("dir");
		FakeDirectory destination = new FakeDirectory("destination");
		root.addFSO(dir);
		root.addFSO(destination);

		assertEquals("", cp.doCommand(workingDir, "dir destination"));
		assertEquals(dir, destination.getContent()[0]);
	}

	@Test
	public void doCommand_dirFromRootToNonexistentSub() {
		FakeDirectory dir = new FakeDirectory("dir");
		root.addFSO(dir);
		assertEquals(cp.COULDNT_COPY, cp.doCommand(workingDir, "dir destination"));
	}

	@Test
	public void equals_symetric() {
		Cp cp1 = new Cp(commandPrompt);
		Cp cp2 = new Cp(commandPrompt);

		assertTrue(cp1.equals(cp2) && cp2.equals(cp1));
	}

	@Test
	public void hashCode_symetric() {
		Cp cp1 = new Cp(commandPrompt);
		Cp cp2 = new Cp(commandPrompt);

		assertTrue(cp1.hashCode() == cp2.hashCode());
	}
}
