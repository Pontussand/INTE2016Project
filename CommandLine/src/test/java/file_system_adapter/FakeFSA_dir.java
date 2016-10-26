package file_system_adapter;

import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFSO;
import file_system_adapter.fake_FSO.FakeFile;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FakeFSA_dir {

	FakeFSAdapter fakeAdapter;
	FakeDirectory root;

	@Before
	public void before() {
		fakeAdapter = new FakeFSAdapter();
		root = new FakeDirectory("root");
		fakeAdapter.setRoot(root);
	}

	@Test
	public void getMkdirsDirs_oneSubFolder(){
		String dirToAdd = "/folder1/folder2";

		ArrayList<String> expected = new ArrayList<>();
		expected.add("/folder1/folder2");
		expected.add("/folder1");

		ArrayList<String> actual = fakeAdapter.getMkdirsDirs(dirToAdd);
		assertEquals(expected, actual);
	}

	@Test
	public void mkdirs_oneDir(){
		fakeAdapter.mkdirs("/folder");
		FakeFSO expected = new FakeDirectory("folder");
		FakeFSO actual = root.getContent()[0];
		assertEquals(expected, actual);
	}

	@Test
	public void mkdirs_twoSubDirs(){
		fakeAdapter.mkdirs("/folder1/folder2");
		FakeFSO expected = new FakeDirectory("folder2");

		FakeDirectory folder1 = (FakeDirectory) root.getContent()[0];
		FakeFSO actual = folder1.getContent()[0];

		assertEquals(expected, actual);
	}

	@Test
	public void mkdirs_threeSubDirs(){
		fakeAdapter.mkdirs("/folder1/folder2/folder3");
		FakeFSO expected = new FakeDirectory("folder3");

		FakeDirectory folder1 = (FakeDirectory) root.getContent()[0];
		FakeDirectory folder2 = (FakeDirectory) folder1.getContent()[0];
		FakeFSO actual = folder2.getContent()[0];

		assertEquals(expected, actual);
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
	public void mkdir_duplicateDir() {
		assertTrue(fakeAdapter.mkdir("/testDir1"));
		assertTrue(!fakeAdapter.mkdir("/testDir1"));
	}

	@Test
	public void mkdir_oneChildDir() {
		FakeDirectory parentDir = new FakeDirectory("parentDir");
		root.addFSO(parentDir);

		assertTrue(fakeAdapter.mkdir("/parentDir/childDir"));

		FakeFSO[] children = parentDir.getContent();
		assertEquals(children.length, 1);
		assertTrue(children[0] instanceof FakeDirectory);
		assertEquals("childDir", children[0].getName());
	}

	@Test
	public void mkdir_parentNotExist() {
		assertTrue(!fakeAdapter.mkdir("/aFile/newDir"));
	}

	@Test
	public void mkdir_parentIsAFile() {
		root.addFSO(new FakeFile("aFile", ""));
		assertTrue(!fakeAdapter.mkdir("/aFile/newDir"));
	}

}
