package file_system_adapter;


import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class FakeFSA_ls {

	FakeFileSystemAdapter fakeAdapter;
	FakeDirectory root;

	@Before
	public void before() {
		fakeAdapter = new FakeFileSystemAdapter();
		root = new FakeDirectory("root");
		fakeAdapter.setRoot(root);
	}


	//TODO: implement more ls tests
	@Test
	public void ls_getListFromRootDir() {
		root.addFSO(new FakeDirectory("Folder1"));
		root.addFSO(new FakeDirectory("Folder2"));

		String[] expected = {"Folder1", "Folder2"};
		String[] actual = fakeAdapter.ls("");

		assertArrayEquals(expected, actual);
	}
}
