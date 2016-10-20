package prompt.command;

import org.junit.*;

import static org.junit.Assert.*;

public class CommandTest {

    Ls ls = new Ls();

    @Before
    public void command() {
        ls = new Ls();
    }

    private String textFileNameLength(int length) {
        String fileName = "";
        for (int a = 0; a < length - 4; a++) {
            fileName += "a";
        }

        return fileName + ".txt";
    }

    @Test
    public void validFSOName_noSpaces() {
        assertEquals(true, ls.validFSOName("abcdef"));
    }

    @Test
    public void validFSOName_Spaces() {
        assertEquals(false, ls.validFSOName("abc def"));
    }

    @Test
    public void validFSOName_invalidCharSmaller() {
        assertEquals(false, ls.validFSOName("<abc"));
    }

    @Test
    public void validFSOName_invalidCharBigger() {
        assertEquals(false, ls.validFSOName(">abc"));
    }

    @Test
    public void validFSOName_invalidCharColon() {
        assertEquals(false, ls.validFSOName(":abc"));
    }

    @Test
    public void validFSOName_invalidCharQuote() {
        assertEquals(false, ls.validFSOName("abc\""));
    }

    @Test
    public void validFSOName_invalidCharSlash() {
        assertEquals(false, ls.validFSOName("/abc"));
    }

    @Test
    public void validFSOName_invalidCharBar() {
        assertEquals(false, ls.validFSOName("|abc"));
    }

    @Test
    public void validFSOName_invalidCharQuestion() {
        assertEquals(false, ls.validFSOName("?abc"));
    }

    @Test
    public void validFSOName_invalidCharAsterisk() {
        assertEquals(false, ls.validFSOName("*abc"));
    }

    @Test
    public void validFSOName_maxLength() {
        assertEquals(true, ls.validFSOName(textFileNameLength(255)));
    }

    @Test
    public void validFSOName_tooLong() {
        assertEquals(false, ls.validFSOName(textFileNameLength(256)));
    }

    @Test
    public void validFSOName_invalidCharBackslash() {
        assertEquals(false, ls.validFSOName("abc\\"));
    }

    @Test
    public void validFileName_wrongFileType() {
        assertEquals(false, ls.validFileName("abc.jpg"));
    }

    @Test
    public void validFileName_rightFileType() {
        assertEquals(true, ls.validFileName("abc.txt"));
    }

}

