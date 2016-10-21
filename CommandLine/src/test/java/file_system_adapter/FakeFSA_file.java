package file_system_adapter;

import file_system_adapter.FakeFileSystemAdapter;
import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FakeFSA_file {
	FakeFileSystemAdapter fakeAdapter;
	FakeDirectory root;

	@Before
	public void before() {
		fakeAdapter = new FakeFileSystemAdapter();
		root = new FakeDirectory("root");
		fakeAdapter.setRoot(root);
	}

	@Test
	public void writeToFile_noSuchFile(){
		assertTrue(!fakeAdapter.writeToFile("notThere.txt", "contents"));
	}

	@Test
	public void writeToFile_emptyFile(){
		FakeFile file = new FakeFile("file.txt", "");
		root.addFSO(file);

		String content = "content";
		assertTrue(fakeAdapter.writeToFile("file.txt", content));
		assertEquals(content, file.getContent());
	}

	@Test
	public void writeToFile_fileNotEmpty(){
		FakeFile file = new FakeFile("file.txt", "this was here before :P");
		root.addFSO(file);

		String content = "content";
		assertTrue(fakeAdapter.writeToFile("file.txt", content));
		assertEquals(content, file.getContent());
	}

	@Test
	public void writeToFile_isDirectory(){
		FakeDirectory file = new FakeDirectory("dir");
		root.addFSO(file);

		String content = "content";
		assertTrue(!fakeAdapter.writeToFile("dir", content));
	}
}
