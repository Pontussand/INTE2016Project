package file_system_adapter;

import file_system_adapter.fake_FSO.FakeDirectory;
import file_system_adapter.fake_FSO.FakeFSO;
import file_system_adapter.fake_FSO.FakeFile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FakeFSA_file {
	FakeFSAdapter fakeAdapter;
	FakeDirectory root;

	@Before
	public void before() {
		fakeAdapter = new FakeFSAdapter();
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

	@Test
	public void createFile_inRoot(){
		fakeAdapter.createFile("/file.txt");
		FakeFSO[] contents = root.getContent();
		assertEquals(1, contents.length);
		assertEquals(contents[0].getName(), "file.txt");
	}

	@Test
	public void createFile_inRootFileExist(){
		root.addFSO(new FakeFile("file.txt", ""));
		assertFalse(fakeAdapter.createFile("/file.txt"));
	}

	@Test
	public void createFile_inSubDir(){
		FakeDirectory sub = new FakeDirectory("sub");
		root.addFSO(sub);

		fakeAdapter.createFile("/sub/file.txt");
		FakeFSO[] contents = sub.getContent();
		assertEquals(1, contents.length);
		assertEquals(contents[0].getName(), "file.txt");
	}

	@Test
	public void createFile_inSubDirFileExist(){
		FakeDirectory sub = new FakeDirectory("sub");
		root.addFSO(sub);
		sub.addFSO(new FakeFile("file.txt", ""));

		assertFalse(fakeAdapter.createFile("/sub/file.txt"));
	}

	@Test
	public void createFile_inRootFileNameEmpty(){
		assertFalse(fakeAdapter.createFile(""));
	}

	@Test
	public void createFile_inSubFileNameEmpty(){
		FakeDirectory sub = new FakeDirectory("sub");
		root.addFSO(sub);

		assertFalse(fakeAdapter.createFile("/sub/"));
	}

	@Test
	public void createFile_inNonexistentSub(){
		assertFalse(fakeAdapter.createFile("/sub/file.txt"));
	}

	@Test
	public void createFile_SubIsAFile(){
		FakeFile sub = new FakeFile("sub", "");
		root.addFSO(sub);

		assertFalse(fakeAdapter.createFile("/sub/file.txt"));
	}

	@Test
	public void isFile_noSuchFileInRoot(){
		assertFalse(fakeAdapter.isFile("/file.txt"));
	}

	@Test
	public void isFile_noSuchFileInSub(){
		assertFalse(fakeAdapter.isFile("/sub/file.txt"));
	}

	@Test
	public void isFile_fileIsDirInRoot(){
		FakeDirectory dir = new FakeDirectory("dir");
		root.addFSO(dir);
		assertFalse(fakeAdapter.isFile("/dir"));
	}

	@Test
	public void isFile_fileIsDirInSub(){
		FakeDirectory sub = new FakeDirectory("sub");
		FakeDirectory dir = new FakeDirectory("dir");
		sub.addFSO(dir);
		root.addFSO(sub);

		assertFalse(fakeAdapter.isFile("/sub/dir"));
	}

	@Test
	public void isFile_fileIsFileInRoot(){
		FakeFile file = new FakeFile("file.txt", "");
		root.addFSO(file);
		assertTrue(fakeAdapter.isFile("/file.txt"));
	}

	@Test
	public void isFile_fileIsFileInSub(){
		FakeDirectory sub = new FakeDirectory("sub");
		FakeFile file = new FakeFile("file.txt", "");
		sub.addFSO(file);
		root.addFSO(sub);

		assertTrue(fakeAdapter.isFile("/sub/file.txt"));
	}
}
