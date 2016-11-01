package prompt.command;

import file_system_adapter.FakeFSAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CdTest {

	private Cd cd;
	private FakeFSAdapter fakeAdapter;
	private FakeDirectory root;

	private PathContainer currentPathContainer;
	private CommandPrompt commandPrompt;

	@Before
	public void before() {
		root = new FakeDirectory("root");
		fakeAdapter = new FakeFSAdapter();
		fakeAdapter.setRoot(root);
		Command.setAdapter(fakeAdapter);

		//root
		currentPathContainer = new PathContainer("");
		commandPrompt = new CommandPrompt(fakeAdapter);

		cd = new Cd(commandPrompt);
	}

	@Test
	public void doCommand_goToSubfolder(){
		FakeDirectory sub = new FakeDirectory("subfolder");
		root.addFSO(sub);

		String ret = cd.doCommand(currentPathContainer, "subfolder");
		assertEquals("", ret);
		assertEquals("/subfolder", currentPathContainer.getPath());
	}

	@Test
	public void doCommand_goToNonExistantSubFolder(){
		String ret = cd.doCommand(currentPathContainer, "Should not exist");
		assertEquals(Cd.NO_SUCH_DIR_MSG, ret);
		assertEquals("", currentPathContainer.getPath());
	}

	@Test
	public void doCommand_goFromRoot(){
		FakeDirectory sub1 = new FakeDirectory("sub1");
		FakeDirectory sub2 = new FakeDirectory("sub2");
		root.addFSO(sub1);
		root.addFSO(sub2);

		currentPathContainer.setPath("/sub1");
		assertEquals("", cd.doCommand(currentPathContainer, "/sub2"));
		assertEquals("/sub2", currentPathContainer.getPath());
	}

	@Test
	public void doCommand_goBackOneDir(){
		FakeDirectory sub = new FakeDirectory("sub");
		root.addFSO(sub);

		currentPathContainer.setPath("/sub");
		assertEquals("", cd.doCommand(currentPathContainer, ".."));
		assertEquals("", currentPathContainer.getPath());
	}

	@Test
	public void doCommand_goToRoot(){
		FakeDirectory sub = new FakeDirectory("sub");
		root.addFSO(sub);

		currentPathContainer.setPath("/sub");
		assertEquals("", cd.doCommand(currentPathContainer, ""));
		assertEquals("", currentPathContainer.getPath());
	}

	@Test
	public void equals_Symmetric() {
		Cd cd1 = new Cd(commandPrompt);
		Cd cd2 = new Cd(commandPrompt);

		assertTrue(cd1.equals(cd2) && cd2.equals(cd1));
	}

	@Test
	public void hashCode_Symetric() {
		Cd cd1 = new Cd(commandPrompt);
		Cd cd2 = new Cd(commandPrompt);

		assertTrue(cd1.hashCode() == cd2.hashCode());
	}

}
