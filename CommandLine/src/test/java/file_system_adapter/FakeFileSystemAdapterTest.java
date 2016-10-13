package file_system_adapter;

import fileSystemObject.FakeDirectory;
import fileSystemObject.FakeFSO;
import org.junit.*;
import static org.junit.Assert.*;

public class FakeFileSystemAdapterTest {
	FakeFileSystemAdapter sa;

	@Before
	public void before(){
		sa = new FakeFileSystemAdapter();
	}

	@Test
	public void mkdirOneDir(){
		FakeDirectory root = new FakeDirectory("root");
		sa.setRoot(root);

		assertTrue(sa.mkdir("/testDir"));

		FakeFSO[] contents = root.getContents();
		assertEquals(contents.length, 1);
		assertTrue(contents[0] instanceof FakeDirectory);
		assertEquals(contents[0].getName(), "testDir");
	}
}
