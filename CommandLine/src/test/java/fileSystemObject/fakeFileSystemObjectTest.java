package fileSystemObject;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class fakeFileSystemObjectTest {
	FakeDirectory root;

	@Before
	public void setup(){
		root = new FakeDirectory("root");
	}

	@Test
	public void mkdirOneDir(){
		FakeDirectory newDir = new FakeDirectory("testDir");
		assertTrue(root.addFSO(newDir));

		FakeFSO[] expectedListing = {new FakeDirectory("testDir")};
		FakeFSO[] listing = root.getContents();

		assertArrayEquals(expectedListing, listing);
	}

	@Test
	public void mkdirTwoDir(){
		FakeDirectory newDir1 = new FakeDirectory("testDir1");
		FakeDirectory newDir2 = new FakeDirectory("testDir2");
		assertTrue(root.addFSO(newDir1));
		assertTrue(root.addFSO(newDir2));

		FakeFSO[] expectedListing = {new FakeDirectory("testDir2"), new FakeDirectory("testDir1")};
		FakeFSO[] listing = root.getContents();
		//System.out.println(listing.length);

		assertArrayEquals(expectedListing, listing);
	}
}
