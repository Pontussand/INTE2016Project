package file_system_adapter.fakeFSO;

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
	public void createFile(){
		FakeFile file = new FakeFile("name", "file contents");
		assertTrue(testDir.addFSO(file));

		FakeFSO[] expectedListing = {new FakeFile("name", "file contents")};
		FakeFSO[] listing = testDir.getContents();

		assertArrayEquals(expectedListing, listing);
	}

	@Test
	public void appendFile(){
		FakeFile file = new FakeFile("name", "This is a");
		file.append(" file.");
		assertEquals("This is a file.", file.getContents());
	}
}
