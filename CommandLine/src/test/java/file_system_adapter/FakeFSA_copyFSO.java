package file_system_adapter;

import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FakeFSA_copyFSO {
	FakeFileSystemAdapter fakeAdapter;
	FakeDirectory root;

	@Before
	public void before() {
		fakeAdapter = new FakeFileSystemAdapter();
		root = new FakeDirectory("");
		fakeAdapter.setRoot(root);
	}

	@Test
	public void copyFSO_fileFromRootToSub() {
		FakeFile file = new FakeFile("file.txt", "");
		FakeDirectory destination = new FakeDirectory("destination");
		root.addFSO(file);
		root.addFSO(destination);

		assertTrue(fakeAdapter.copyFSO("/file.txt", "/destination"));
		assertEquals(file, destination.getContent()[0]);
		assertNotSame(file, destination.getContent()[0]);

		// file comes before destination in sorting.
		assertSame(file, root.getContent()[1]);
	}

	@Test
	public void copyFSO_emptyDirFromRootToSub() {
		FakeDirectory dir = new FakeDirectory("dir");
		FakeDirectory destination = new FakeDirectory("destination");
		root.addFSO(dir);
		root.addFSO(destination);

		assertTrue(fakeAdapter.copyFSO("/dir", "/destination"));
		assertEquals(dir, destination.getContent()[0]);
	}

	@Test
	public void copyFSO_filledDirFromRootToSub() {
		FakeDirectory[] expectedDirContent = {new FakeDirectory("1"), new FakeDirectory("2"), new FakeDirectory("3")};

		FakeDirectory dir = new FakeDirectory("dir");
		dir.addFSO(expectedDirContent[0]);
		dir.addFSO(expectedDirContent[1]);
		dir.addFSO(expectedDirContent[2]);
		root.addFSO(dir);

		FakeDirectory destination = new FakeDirectory("destination");
		root.addFSO(destination);

		assertTrue(fakeAdapter.copyFSO("/dir", "/destination"));

		FakeDirectory destContent = (FakeDirectory) destination.getContent()[0];
		assertArrayEquals(expectedDirContent, destContent.getContent());
	}

	@Test
	public void copyFSO_filledDirFromSub1ToSub2() {
		FakeDirectory[] expectedDirContent = {new FakeDirectory("1"), new FakeDirectory("2"), new FakeDirectory("3")};

		FakeDirectory source = new FakeDirectory("source");
		source.addFSO(expectedDirContent[0]);
		source.addFSO(expectedDirContent[1]);
		source.addFSO(expectedDirContent[2]);
		FakeDirectory sub = new FakeDirectory("sub");
		sub.addFSO(source);
		root.addFSO(sub);


		FakeDirectory destination = new FakeDirectory("destination");
		root.addFSO(destination);

		assertTrue(fakeAdapter.copyFSO("/sub/source", "/destination"));

		FakeDirectory src = (FakeDirectory) destination.getContent()[0];
		assertNotSame(source, src);
		assertEquals(source, src);
		assertArrayEquals(expectedDirContent, src.getContent());
	}

}
