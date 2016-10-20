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
}
