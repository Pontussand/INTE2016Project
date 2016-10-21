package prompt.command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import org.junit.Before;
import org.junit.Test;
import prompt.CommandPrompt;
import prompt.util.PathContainer;

import static org.junit.Assert.assertEquals;

public class CdTest {

	private Cd cd;
	private FakeFileSystemAdapter fakeAdapter;
	private FakeDirectory root;

	private PathContainer currentPathContainer;

	@Before
	public void before() {
		root = new FakeDirectory("root");
		fakeAdapter = new FakeFileSystemAdapter();
		fakeAdapter.setRoot(root);
		Command.setAdapter(fakeAdapter);

		//root
		currentPathContainer = new PathContainer("");

		cd = new Cd(new CommandPrompt(fakeAdapter));
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

}
