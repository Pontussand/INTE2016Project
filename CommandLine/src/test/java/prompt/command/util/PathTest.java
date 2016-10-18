package prompt.command.util;

import org.junit.Test;
import prompt.util.Path;

import static org.junit.Assert.assertEquals;

public class PathTest {

	@Test
	public void getFSOName_fromRootDir(){
		String expected = "Dir";
		String actual = Path.getFSOName("/Dir");
		assertEquals(expected, actual);
	}

	@Test
	public void getFSOName_fromSubDir(){
		String expected = "Dir";
		String actual = Path.getFSOName("/folder1/folder2/Dir");
		assertEquals(expected, actual);
	}

	@Test
	public void getParentDirPath_rootDir(){
		String expected = "";
		String actual = Path.getParentPath("/Dir");
		assertEquals(expected, actual);
	}

	@Test
	public void getParentDirPath_fromSubDir(){
		String expected = "/folder1/folder2";
		String actual = Path.getParentPath("/folder1/folder2/Dir");
		assertEquals(expected, actual);
	}
}
