package file_system_adapter;

import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFSO;
import file_system_adapter.fake_FSO.FakeFile;
import org.junit.*;

import static org.junit.Assert.*;

public class FakeFileSystemAdapterTest {
	FakeFileSystemAdapter fakeAdapter;
	FakeDirectory root;

	@Before
	public void before() {
		fakeAdapter = new FakeFileSystemAdapter();
		root = new FakeDirectory("root");
		fakeAdapter.setRoot(root);
	}

	@Test
	public void isDir_rootDir() {
		root.addFSO(new FakeDirectory("Directory"));
		assertTrue(fakeAdapter.isDir("/Directory"));
	}

	@Test
	public void isDir_SubFolderDir() {
		FakeDirectory folder1 = new FakeDirectory("Folder1");
		FakeDirectory dir = new FakeDirectory("Directory");

		folder1.addFSO(dir);
		root.addFSO(folder1);
		assertTrue(fakeAdapter.isDir("/Folder1/Directory"));
	}

	@Test
	public void isDir_rootFile() {
		root.addFSO(new FakeFile("Directory", ""));
		assertTrue(!fakeAdapter.isDir("/Directory"));
	}

	@Test
	public void isDir_SubFolderFile() {
		FakeDirectory folder1 = new FakeDirectory("Folder1");
		FakeFile file = new FakeFile("Directory", "");

		folder1.addFSO(file);
		root.addFSO(folder1);
		assertTrue(!fakeAdapter.isDir("/Folder1/Directory"));
	}

	@Test
	public void mkdir_oneRootDir() {
		assertTrue(fakeAdapter.mkdir("/testDir"));

		FakeFSO[] contents = root.getContent();
		assertEquals(contents.length, 1);
		assertTrue(contents[0] instanceof FakeDirectory);
		assertEquals("testDir", contents[0].getName());
	}

	@Test
	public void mkdir_twoRootDir() {
		assertTrue(fakeAdapter.mkdir("/testDir1"));
		assertTrue(fakeAdapter.mkdir("/testDir2"));

		FakeFSO[] contents = root.getContent();
		assertEquals(contents.length, 2);
		assertTrue(contents[0] instanceof FakeDirectory);
		assertEquals("testDir1", contents[0].getName());
		assertEquals("testDir2", contents[1].getName());
	}

	@Test
	public void mkdir_oneChildDir() {
		assertTrue(fakeAdapter.mkdir("/parentDir"));
		assertTrue(fakeAdapter.mkdir("/parentDir/childDir"));

		FakeFSO[] contents = root.getContent();
		assertEquals(contents.length, 2);
		assertTrue(contents[0] instanceof FakeDirectory);
		assertEquals("testDir1", contents[0].getName());
		assertEquals("testDir2", contents[1].getName());
	}

	@Test
	public void ls_getListFromPath() {
		fakeAdapter.mkdir("/Folder1");
		fakeAdapter.mkdir("/Folder1/Folder2");
		fakeAdapter.mkdir("/Folder1/Folder3");


	}


}
