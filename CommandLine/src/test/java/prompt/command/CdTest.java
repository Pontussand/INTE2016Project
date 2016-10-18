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
		currentPath = new Path(Path.DIR_SEPERATOR);
	}

	@Test
	public void doCommand_goToSubfolder(){
		FakeDirectory sub = new FakeDirectory("subfolder");
		root.addFSO(sub);

		cd.doCommand(currentPath, "subfolder");
		assertEquals("/subfolder", currentPath.getPath());
	}



}
