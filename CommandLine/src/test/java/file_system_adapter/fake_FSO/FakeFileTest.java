package file_system_adapter.fake_FSO;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

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
		FakeFSO[] listing = testDir.getContent();

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
		assertEquals("This is a file.", file.getContent());
	}

	@Test
	public void equals_toSameObject(){
		FakeFile file = new FakeFile("file.txt", "");
		assertTrue(file.equals(file));
	}

	@Test
	public void equals_toEqualFakeFileNameAndContent(){
		FakeFile file1 = new FakeFile("file.txt", "A");
		FakeFile file2 = new FakeFile("file.txt", "A");

		assertTrue(file1.equals(file2));
	}

	@Test
	public void equals_toEqualFakeFileNameDifferentContent(){
		FakeFile file1 = new FakeFile("file.txt", "A");
		FakeFile file2 = new FakeFile("file.txt", "B");

		assertFalse(file1.equals(file2));
	}

	@Test
	public void equals_toEqualFakeDir(){
		FakeFile file1 = new FakeFile("file.txt", "");
		FakeDirectory dir = new FakeDirectory("file.txt");
		assertTrue(file1.equals(dir));
	}
}
