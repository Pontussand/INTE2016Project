package prompt;

/**
 * Created by pontus on 2016-10-13.
 */

import org.junit.*;
import static org.junit.Assert.*;

public class FileManager_InputControlTest {

	Filemanager fm = new Filemanager();

	@Before
	public void filemanager() {
		fm = new Filemanager();
	}

	private String textFileNameLength(int length) {
		String fileName = "";
		for (int a = 0; a < length - 4; a++) {
			fileName += "a";
		}

		return fileName + ".txt";
	}

	@Test
	public void correctFileName_noSpaces() {
		Filemanager fm = new Filemanager();
		assertEquals(true, fm.correctFileName("abcdef.txt"));
	}

	@Test
	public void correctFileName_Spaces() {
		assertEquals(false, fm.correctFileName("abc def"));
	}

	@Test
	public void correctFileName_wrongFileType() {
		Filemanager fm = new Filemanager();
		assertEquals(false, fm.correctFileName("abc.jpg"));
	}

	@Test
	public void correctFileName_rightFileType() {
		Filemanager fm = new Filemanager();
		assertEquals(true, fm.correctFileName("abc.txt"));
	}

	@Test
	public void correctFileName_unvalidCharSmaller() {
		Filemanager fm = new Filemanager();
		assertEquals(false, fm.correctFileName("<abc.txt"));
	}

	@Test
	public void correctFileName_unvalidCharBigger() {
		Filemanager fm = new Filemanager();
		assertEquals(false, fm.correctFileName(">abc.txt"));
	}

	@Test
	public void correctFileName_unvalidCharColon() {
		Filemanager fm = new Filemanager();
		assertEquals(false, fm.correctFileName(":abc.txt"));
	}

	@Test
	public void correctFileName_unvalidCharQuote() {
		Filemanager fm = new Filemanager();
		assertEquals(false, fm.correctFileName("abc\".txt"));
	}

	@Test
	public void correctFileName_unvalidCharSlash() {
		Filemanager fm = new Filemanager();
		assertEquals(false, fm.correctFileName("/abc.txt"));
	}

	@Test
	public void correctFileName_unvalidCharBackslash() {
		Filemanager fm = new Filemanager();
		assertEquals(false, fm.correctFileName("abc\\.txt"));
	}

	@Test
	public void correctFileName_unvalidCharBar() {
		Filemanager fm = new Filemanager();
		assertEquals(false, fm.correctFileName("|abc.txt"));
	}

	@Test
	public void correctFileName_unvalidCharQuestion() {
		Filemanager fm = new Filemanager();
		assertEquals(false, fm.correctFileName("?abc.txt"));
	}

	@Test
	public void correctFileName_unvalidCharAsterisk() {
		Filemanager fm = new Filemanager();
		assertEquals(false, fm.correctFileName("*abc.txt"));
	}

	@Test
	public void command_fileNameTooLong() {
		assertEquals(false, fm.correctFileName(textFileNameLength(256)));
	}

	@Test
	public void command_fileNameMaxLength() {
		assertEquals(true, fm.correctFileName(textFileNameLength(255)));
	}

	@Test
	public void correctDirectoryName_noSpaces() {
		Filemanager fm = new Filemanager();
		assertEquals(true, fm.correctDirectoryName("\\abcdef"));
	}
}