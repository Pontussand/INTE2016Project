package file_system_adapter.fake_FSO;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FakeFileTest {
	FakeDirectory testDir;

	@Before
	public void setup(){
		testDir = new FakeDirectory("testDir");
	}

	@Test
	public void addFSO_fileInRoot(){
		FakeFile file = new FakeFile("name", "file contents");
		assertTrue(testDir.addFSO(file));

		FakeFSO[] expectedListing = {new FakeFile("name", "file contents")};
		FakeFSO[] listing = testDir.getContents();

		assertArrayEquals(expectedListing, listing);
	}

	@Test(expected=IllegalArgumentException.class)
	public void constructor_nullName(){
		FakeFile file = new FakeFile(null, "");
	}

	@Test
	public void constructor_nullContents(){
		FakeFile file = new FakeFile("name", null);
		FakeFile expectedOutput = new FakeFile("name", "");
		assertEquals(file, expectedOutput);
	}

	@Test
	public void append(){
		FakeFile file = new FakeFile("name", "This is a");
		file.append(" file.");
		assertEquals("This is a file.", file.getContents());
	}

}
