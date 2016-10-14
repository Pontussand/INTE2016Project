package command.test;

import command.CommandPrompt;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CommandPromtTest {

    private static String ls_filename = "ls abc";
    private static String ls_expected = "ls fil: abc";
    private static String mkdir_filename = "mkdir abc";
    private static String mkdir_expected = "mkdir fil: abc";
    private static String wrongCommand = "Command doesn't exit";

    private CommandPrompt cmdprompt;

    @Before
    public void setUp() {
        cmdprompt = new CommandPrompt();
    }

/*    @Test
    public void commandCommandAndFilename(){
        CommandPrompt cp = new CommandPrompt();
        assertEquals("abc fil: def", cp.processCommand("abc def"));
    }*/

    @Test
    public void command_lsFileName(){
        assertEquals(ls_expected, cmdprompt.processCommand(ls_filename));
    }

    @Test
    public void command_mkdirFileName(){
        assertEquals(mkdir_expected, cmdprompt.processCommand(mkdir_filename));
    }

    @Test
    public void command_wrongCommand(){
        assertEquals(wrongCommand, cmdprompt.processCommand("3 abc"));
    }

    @Test
    public void command_lsNoFileName(){
        assertEquals("ls fil: ", cmdprompt.processCommand("ls "));
    }

    @Test
    public void command_pwd(){
        assertEquals("pwd", cmdprompt.processCommand("pwd"));
    }

    @Test
    public void command_lsFileNameNoSpace(){
        assertEquals(wrongCommand, cmdprompt.processCommand("lsabc"));
    }
}
