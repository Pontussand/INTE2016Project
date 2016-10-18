package file_system_adapter.fake_FSO;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class FakeDirectoryTest {
    FakeDirectory testDir;

    @Before
    public void setup() {
        testDir = new FakeDirectory("testDir");
    }

    @Test
    public void addFSO_OneDir() {
        FakeDirectory newDir = new FakeDirectory("name");
        assertTrue(testDir.addFSO(newDir));

        FakeFSO[] expectedListing = {new FakeDirectory("name")};
        FakeFSO[] listing = testDir.getContent();

        assertArrayEquals(expectedListing, listing);
    }

    @Test
    public void addFSO_DuplicateDirs() {
        FakeDirectory original = new FakeDirectory("name");
        FakeDirectory duplicate = new FakeDirectory("name");
        assertTrue(testDir.addFSO(original));
        assertTrue(!testDir.addFSO(duplicate));

        FakeFSO[] expectedListing = {new FakeDirectory("name")};
        FakeFSO[] listing = testDir.getContent();

        assertArrayEquals(expectedListing, listing);
    }

    @Test
    public void addFSO_TwoDir() {
        FakeDirectory dirA = new FakeDirectory("A");
        FakeDirectory dirB = new FakeDirectory("B");
        assertTrue(testDir.addFSO(dirA));
        assertTrue(testDir.addFSO(dirB));

        //Order matters!
        FakeFSO[] expectedListing = {new FakeDirectory("A"), new FakeDirectory("B")};
        FakeFSO[] listing = testDir.getContent();

        assertArrayEquals(expectedListing, listing);
    }

    @Test
    public void addFSO_TwoDirReverseOrder() {
        FakeDirectory dirB = new FakeDirectory("B");
        FakeDirectory dirA = new FakeDirectory("A");
        //note that the input order have changed
        assertTrue(testDir.addFSO(dirB));
        assertTrue(testDir.addFSO(dirA));

        FakeFSO[] expectedListing = {new FakeDirectory("A"), new FakeDirectory("B")};
        FakeFSO[] listing = testDir.getContent();

        assertArrayEquals(expectedListing, listing);
    }

    @Test
    public void pathsearch_findInLargerStructure() {
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

        FakeFSO result = testDir.pathSearch("/AA/BB");
        assertTrue(result instanceof FakeDirectory);
        assertSame(BB, result);
    }

    @Test
    public void pathSearch_findRoot() {
        FakeDirectory decoy = new FakeDirectory("root");
        testDir.addFSO(decoy);
        FakeFSO result = testDir.pathSearch("");
        assertSame(testDir, result);
    }


    @Test
    public void pathSearch_readFile() {
        FakeDirectory fakeRoot = new FakeDirectory("Root");
        fakeRoot.addFSO(new FakeDirectory("texter"));
        FakeDirectory anotherDir = (FakeDirectory) fakeRoot.pathSearch("/texter");
        anotherDir.addFSO(new FakeFile("text.txt", "Once upon a time..."));

        FakeFile fakeFile = (FakeFile) fakeRoot.pathSearch("/texter/text.txt");
        String fakeFileString = fakeFile.getContent();

        assertEquals(fakeFileString, "Once upon a time...");
    }

    @Test
    public void getContents_listFiles() {
        FakeDirectory fakeDir = new FakeDirectory("/Home");
        fakeDir.addFSO(new FakeDirectory("First"));
        fakeDir.addFSO(new FakeDirectory("Second"));

        FakeFSO[] expectedContent = new FakeFSO[2];
        expectedContent[0] = new FakeDirectory("First");
        expectedContent[1] = new FakeDirectory("Second");

        assertArrayEquals(expectedContent, fakeDir.getContent());
    }




}
