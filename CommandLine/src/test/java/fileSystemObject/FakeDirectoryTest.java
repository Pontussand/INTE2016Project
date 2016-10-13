package fileSystemObject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;


public class FakeDirectoryTest {
	FakeDirectory testDir;

	@Before
	public void setup(){
		testDir = new FakeDirectory("testDir");
	}

	@Test
	public void mkdirOneDir(){
		FakeDirectory newDir = new FakeDirectory("name");
		assertTrue(testDir.addFSO(newDir));

		FakeFSO[] expectedListing = {new FakeDirectory("name")};
		FakeFSO[] listing = testDir.getContents();

		assertArrayEquals(expectedListing, listing);
	}

	@Test
	public void mkdirDuplicate(){
		FakeDirectory original = new FakeDirectory("name");
		FakeDirectory duplicate = new FakeDirectory("name");
		assertTrue(testDir.addFSO(original));
		assertTrue(!testDir.addFSO(duplicate));

		FakeFSO[] expectedListing = {new FakeDirectory("name")};
		FakeFSO[] listing = testDir.getContents();

		assertArrayEquals(expectedListing, listing);
	}

	@Test
	public void mkdirTwoDir(){
		FakeDirectory dirA = new FakeDirectory("A");
		FakeDirectory dirB = new FakeDirectory("B");
		assertTrue(testDir.addFSO(dirA));
		assertTrue(testDir.addFSO(dirB));

		//Order matters!
		FakeFSO[] expectedListing = {new FakeDirectory("A"), new FakeDirectory("B")};
		FakeFSO[] listing = testDir.getContents();

		assertArrayEquals(expectedListing, listing);
	}

	@Test
	public void mkdirTwoDirReverseOrder(){
		FakeDirectory dirB = new FakeDirectory("B");
		FakeDirectory dirA = new FakeDirectory("A");
		//note that the input order have changed
		assertTrue(testDir.addFSO(dirB));
		assertTrue(testDir.addFSO(dirA));

		FakeFSO[] expectedListing = {new FakeDirectory("A"), new FakeDirectory("B")};
		FakeFSO[] listing = testDir.getContents();

		assertArrayEquals(expectedListing, listing);
	}
}
