package prompt.command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import org.junit.Before;
import org.junit.Test;
import prompt.util.Path;

import static org.junit.Assert.assertEquals;

public class CdTest {

	private Cd cd;
	private FakeFileSystemAdapter fakeAdapter;
	private FakeDirectory root;

	private Path currentPath;

	@Before
	public void before() {
		cd = new Cd();

		root = new FakeDirectory("root");
		fakeAdapter = new FakeFileSystemAdapter();
		fakeAdapter.setRoot(root);
		Command.setAdapter(fakeAdapter);

		//root
		currentPath = new Path("");
	}

	@Test
	public void doCommand_goToSubfolder(){
		FakeDirectory sub = new FakeDirectory("subfolder");
		root.addFSO(sub);

		String ret = cd.doCommand(currentPath, "subfolder");
		assertEquals("", ret);
		assertEquals("/subfolder", currentPath.getPath());
	}

	@Test
	public void doCommand_goToNonExistantSubFolder(){
		String ret = cd.doCommand(currentPath, "Should not exist");
		assertEquals(Cd.NO_SUCH_DIR_MSG, ret);
		assertEquals("", currentPath.getPath());
	}

	@Test
	public void doCommand_goFromRoot(){
		FakeDirectory sub1 = new FakeDirectory("sub1");
		FakeDirectory sub2 = new FakeDirectory("sub2");
		root.addFSO(sub1);
		root.addFSO(sub2);

		currentPath.setPath("/sub1");
		assertEquals("", cd.doCommand(currentPath, "/sub2"));
		assertEquals("/sub2", currentPath.getPath());
	}

	@Test
	public void doCommand_goBackOneDir(){
		FakeDirectory sub = new FakeDirectory("sub");
		root.addFSO(sub);

		currentPath.setPath("/sub");
		assertEquals("", cd.doCommand(currentPath, ".."));
		assertEquals("", currentPath.getPath());
	}

	@Test
	public void doCommand_goToRoot(){
		FakeDirectory sub = new FakeDirectory("sub");
		root.addFSO(sub);

		currentPath.setPath("/sub");
		assertEquals("", cd.doCommand(currentPath, ""));
		assertEquals("", currentPath.getPath());
	}

}
