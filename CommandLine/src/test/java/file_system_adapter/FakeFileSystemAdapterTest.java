package file_system_adapter;

import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFSO;
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
	public void getFSOName_fromRootDir(){
		String expected = "Dir";
		String actual = fakeAdapter.getFSOName("/Dir");
		assertEquals(expected, actual);
	}

	@Test
	public void getFSOName_fromSubDir(){
		String expected = "Dir";
		String actual = fakeAdapter.getFSOName("/folder1/folder2/Dir");
		assertEquals(expected, actual);
	}

	@Test
	public void getParentDirPath_rootDir(){
		String expected = "";
		String actual = fakeAdapter.getParentDirPath("/Dir");
		assertEquals(expected, actual);
	}

	@Test
	public void getParentDirPath_fromSubDir(){
		String expected = "/folder1/folder2";
		String actual = fakeAdapter.getParentDirPath("/folder1/folder2/Dir");
		assertEquals(expected, actual);
	}

	@Test
	public void mkdir_oneRootDir() {
//		FakeDirectory root = new FakeDirectory("root");
//		fakeAdapter.setRoot(root);

		assertTrue(fakeAdapter.mkdir("/testDir"));

		FakeFSO[] contents = root.getContent();
		assertEquals(contents.length, 1);
		assertTrue(contents[0] instanceof FakeDirectory);
		assertEquals("testDir", contents[0].getName());
	}

	@Test
	public void mkdir_twoRootDir() {
//		FakeDirectory root = new FakeDirectory("root");
//		fakeAdapter.setRoot(root);

		assertTrue(fakeAdapter.mkdir("/testDir1"));
		assertTrue(fakeAdapter.mkdir("/testDir2"));

		FakeFSO[] contents = root.getContent();
		assertEquals(contents.length, 2);
		assertTrue(contents[0] instanceof FakeDirectory);
		assertEquals("testDir1", contents[0].getName());
		assertEquals("testDir2", contents[1].getName());
	}

	@Test
	public void mkdir_oneChildDir(){
//		FakeDirectory root = new FakeDirectory("root");
//		fakeAdapter.setRoot(root);

		FakeDirectory parentSDir = new FakeDirectory("parentDir");
		root.addFSO(parentSDir);
		assertTrue(fakeAdapter.mkdir("/parentDir/childDir"));

		FakeFSO[] contents = parentSDir.getContent();
		assertEquals(contents.length, 1);
		assertTrue(contents[0] instanceof FakeDirectory);
		assertEquals("childDir", contents[0].getName());
	}

	@Test
	public void ls_getListFromPath() {
		fakeAdapter.mkdir("/Folder1");
		fakeAdapter.mkdir("/Folder1/Folder2");
		fakeAdapter.mkdir("/Folder1/Folder3");

		String[] expected = new String[2];
		expected[0] = "Folder2";
		expected[1] = "Folder3";

		assertArrayEquals(expected, fakeAdapter.ls("/Folder1"));

//		not finished!


	}


}
