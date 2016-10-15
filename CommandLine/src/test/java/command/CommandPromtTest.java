package command;

import org.junit.*;
import static org.junit.Assert.*;

public class CommandPromtTest {

    private static String ls_filename = "ls abc";
    private static String ls_expected = "ls fil: abc";
    private static String mkdir_filename = "mkdir abc";
    private static String mkdir_expected = "mkdir fil: abc";
    private static String wrongCommand = "Command doesn't exit";
    private static String incorrectFileName = "Incorrect fileName";
    private static String correctFileName = "Correct fileName";

    CommandPrompt cp;

    @Before
    public void commandprompt(){
        cp = new CommandPrompt();
    }

    @Test
    public void command_lsFileName(){
        assertEquals(ls_expected, cp.command(ls_filename));
    }

    @Test
    public void command_mkdirFileName(){
        assertEquals(mkdir_expected, cp.command(mkdir_filename));
    }

    @Test
    public void command_wrongCommand(){
        assertEquals(wrongCommand, cp.command("3 abc"));
    }

    @Test
    public void command_lsNoFileName(){
        assertEquals("ls fil: ", cp.command("ls "));
    }

    @Test
    public void command_pwd(){
        assertEquals("pwd", cp.command("pwd"));
    }

    @Test
    public void command_lsFileNameNoSpace(){
        assertEquals(wrongCommand, cp.command("lsabc"));
    }
}
