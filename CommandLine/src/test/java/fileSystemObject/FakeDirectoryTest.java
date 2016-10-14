package fileSystemObject;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


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

	@Test
	public void pathSearch(){
		// testDir/AA/BB/CC
		// "find testDir/AA/BB" should return BB Fake Directory object
		FakeDirectory AA = new FakeDirectory("AA");
		FakeDirectory AB = new FakeDirectory("AB");
		FakeDirectory AC = new FakeDirectory("AC");
		testDir.addFSO(AA);
		testDir.addFSO(AB);
		testDir.addFSO(AC);

		FakeDirectory BA = new FakeDirectory("BA");
		FakeDirectory BB = new FakeDirectory("BB");
		FakeDirectory BC = new FakeDirectory("BC");
		AA.addFSO(BA);
		AA.addFSO(BB);
		AA.addFSO(BC);

		FakeDirectory CA = new FakeDirectory("CA");
		FakeDirectory CB = new FakeDirectory("CB");
		FakeDirectory CC = new FakeDirectory("CC");
		BB.addFSO(CA);
		BB.addFSO(CB);
		BB.addFSO(CC);

		FakeFSO result = testDir.pathSearch("AA/BB");
		assertTrue(result instanceof FakeDirectory);
		assertSame(BB, result);
	}
}
