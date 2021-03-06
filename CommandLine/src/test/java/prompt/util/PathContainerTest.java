package prompt.util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PathContainerTest {

	@Test
	public void getFSOName_fromRootDir(){
		String expected = "Dir";
		String actual = PathContainer.getFSOName("/Dir");
		assertEquals(expected, actual);
	}

	@Test
	public void getFSOName_fromSubDir(){
		String expected = "Dir";
		String actual = PathContainer.getFSOName("/folder1/folder2/Dir");
		assertEquals(expected, actual);
	}

	@Test
	public void getParentDirPath_rootDir(){
		String expected = "";
		String actual = PathContainer.getParentPath("/Dir");
		assertEquals(expected, actual);
	}

	@Test
	public void getParentDirPath_fromSubDir(){
		String expected = "/folder1/folder2";
		String actual = PathContainer.getParentPath("/folder1/folder2/Dir");
		assertEquals(expected, actual);
	}

	@Test
	public void getFullPath_fromRootToAbsolutePath(){
		String expected = "/folder1";
		String actual = PathContainer.getFullPath("", "/folder1");
		assertEquals(expected, actual);
	}

	@Test
	public void getFullPath_fromRootToRelativePath(){
		String expected = "/folder1";
		String actual = PathContainer.getFullPath("", "folder1");
		assertEquals(expected, actual);
	}

	@Test
	public void getFullPath_fromSubdirToAbsolutePath(){
		String expected = "/subdir/folder1";
		String actual = PathContainer.getFullPath("/subdir", "/subdir/folder1");
		assertEquals(expected, actual);
	}

	@Test
	public void getFullPath_fromSubdirToRelativePath(){
		String expected = "/subdir/folder1";
		String actual = PathContainer.getFullPath("/subdir", "folder1");
		assertEquals(expected, actual);
	}
	
	@Test
	public void append_anyString(){
		String before = "/folder/folder";
		PathContainer container = new PathContainer(before);
		String toAppend = "appendant";
		String expected = before + toAppend;
		container.append(toAppend);

		assertEquals(expected, container.getPath());
	}

	@Test
	public void objGetFSOName_fromRootDir(){
		String expected = "FSOname";
		String path = "" + expected;
		PathContainer container = new PathContainer(path);
		assertEquals(expected, container.getFSOName());
	}

	@Test
	public void objGetFSOName_fromSubDir(){
		String expected = "FSOname";
		String path = "folder1/folder2/"+expected;
		PathContainer container = new PathContainer(path);
		assertEquals(expected, container.getFSOName());
	}

}
