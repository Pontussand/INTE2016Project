package file_system_adapter;

import file_system_adapter.fakeFSO.FakeDirectory;
import file_system_adapter.fakeFSO.FakeFSO;
import org.junit.*;
import static org.junit.Assert.*;

public class FakeFileSystemAdapterTest {
	FakeFileSystemAdapter sa;

	@Before
	public void before(){
		sa = new FakeFileSystemAdapter();
	}

	@Test
	public void mkdirOneRootDir(){
		FakeDirectory root = new FakeDirectory("root");
		sa.setRoot(root);

		assertTrue(sa.mkdir("testDir"));

		FakeFSO[] contents = root.getContents();
		assertEquals(contents.length, 1);
		assertTrue(contents[0] instanceof FakeDirectory);
		assertEquals("testDir", contents[0].getName());
	}
}
