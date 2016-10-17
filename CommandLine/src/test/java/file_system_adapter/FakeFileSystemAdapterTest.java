package file_system_adapter;

import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFSO;
import org.junit.*;

import static org.junit.Assert.*;

public class FakeFileSystemAdapterTest {
	FakeFileSystemAdapter sa;

	@Before
	public void before() {
		sa = new FakeFileSystemAdapter();
	}

	@Test
	public void getFSOName_fromRootDir(){
		String expected = "Dir";
		String actual = sa.getFSOName("/Dir");
		assertEquals(expected, actual);
	}

	@Test
	public void getFSOName_fromSubDir(){
		String expected = "Dir";
		String actual = sa.getFSOName("/folder1/folder2/Dir");
		assertEquals(expected, actual);
	}

	@Test
	public void getParentDirPath_rootDir(){
		String expected = "";
		String actual = sa.getParentDirPath("/Dir");
		assertEquals(expected, actual);
	}

	@Test
	public void getParentDirPath_fromSubDir(){
		String expected = "/folder1/folder2";
		String actual = sa.getParentDirPath("/folder1/folder2/Dir");
		assertEquals(expected, actual);
	}

	@Test
	public void mkdir_oneRootDir() {
		FakeDirectory root = new FakeDirectory("root");
		sa.setRoot(root);

		assertTrue(sa.mkdir("/testDir"));

		FakeFSO[] contents = root.getContent();
		assertEquals(contents.length, 1);
		assertTrue(contents[0] instanceof FakeDirectory);
		assertEquals("testDir", contents[0].getName());
	}

	@Test
	public void mkdir_twoRootDir() {
		FakeDirectory root = new FakeDirectory("root");
		sa.setRoot(root);

		assertTrue(sa.mkdir("/testDir1"));
		assertTrue(sa.mkdir("/testDir2"));

		FakeFSO[] contents = root.getContent();
		assertEquals(contents.length, 2);
		assertTrue(contents[0] instanceof FakeDirectory);
		assertEquals("testDir1", contents[0].getName());
		assertEquals("testDir2", contents[1].getName());
	}

	@Test
	public void mkdir_oneChildDir(){
		FakeDirectory root = new FakeDirectory("root");
		sa.setRoot(root);

		assertTrue(sa.mkdir("/parentDir"));
		assertTrue(sa.mkdir("/parentDir/childDir"));

		FakeFSO[] contents = root.getContent();
		assertEquals(contents.length, 2);
		assertTrue(contents[0] instanceof FakeDirectory);
		assertEquals("testDir1", contents[0].getName());
		assertEquals("testDir2", contents[1].getName());
	}


}
