package prompt.command;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFSO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class CdTest {

	private Ls ls;
	private FakeFileSystemAdapter fakeAdapter;
	private FakeDirectory root;

	@Before
	public void before() {
		ls = new Ls();
		fakeAdapter = new FakeFileSystemAdapter();
		root = new FakeDirectory("root");
		fakeAdapter.setRoot(root);
		ls.setAdapter(fakeAdapter);
	}



}
