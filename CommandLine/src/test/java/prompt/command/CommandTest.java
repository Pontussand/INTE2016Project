package prompt.command;

import org.junit.*;

import static org.junit.Assert.*;

public class CommandTest {

    private String textFileNameLength(int length) {
        String fileName = "";
        for (int a = 0; a < length - 4; a++) {
            fileName += "a";
        }
        return fileName + ".txt";
    }

    @Test
    public void validFSOName_noSpaces() {
        assertEquals(true, Command.validFSOName("abcdef"));
    }

    @Test
    public void validFSOName_Spaces() {
        assertEquals(false, Command.validFSOName("abc def"));
    }

    @Test
    public void validFSOName_invalidCharSmaller() {
        assertEquals(false, Command.validFSOName("<abc"));
    }

    @Test
    public void validFSOName_invalidCharBigger() {
        assertEquals(false, Command.validFSOName(">abc"));
    }

    @Test
    public void validFSOName_invalidCharColon() {
        assertEquals(false, Command.validFSOName(":abc"));
    }

    @Test
    public void validFSOName_invalidCharQuote() {
        assertEquals(false, Command.validFSOName("abc\""));
    }

    @Test
    public void validFSOName_invalidCharSlash() {
        assertEquals(false, Command.validFSOName("/abc"));
    }

    @Test
    public void validFSOName_invalidCharBar() {
        assertEquals(false, Command.validFSOName("|abc"));
    }

    @Test
    public void validFSOName_invalidCharQuestion() {
        assertEquals(false, Command.validFSOName("?abc"));
    }

    @Test
    public void validFSOName_invalidCharAsterisk() {
        assertEquals(false, Command.validFSOName("*abc"));
    }

    @Test
    public void validFSOName_maxLength() {
        assertEquals(true, Command.validFSOName(textFileNameLength(255)));
    }

    @Test
    public void validFSOName_tooLong() {
        assertEquals(false, Command.validFSOName(textFileNameLength(256)));
    }

    @Test
    public void validFSOName_invalidCharBackslash() {
        assertEquals(false, Command.validFSOName("abc\\"));
    }

    @Test
    public void validFileName_wrongFileType() {
        assertEquals(false, Command.validFileName("abc.jpg"));
    }

    @Test
    public void validFileName_rightFileType() {
        assertEquals(true, Command.validFileName("abc.txt"));
    }

}

