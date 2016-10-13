package command;

import org.junit.*;
import static org.junit.Assert.*;

public class CommandPromtTest {

    private static String ls_filename = "ls abc";
    private static String ls_expected = "ls fil: abc";
    private static String mkdir_filename = "mkdir abc";
    private static String mkdir_expected = "mkdir fil: abc";
    private static String wrongCommand = "Command doesn't exit";

//    @Test
//    public void commandCommandAndFilename(){
//        CommandPrompt cp = new CommandPrompt();
//        assertEquals("abc fil: def", cp.command("abc def"));
//    }

    @Test
    public void command_lsFileName(){
        CommandPrompt cp = new CommandPrompt();
        assertEquals(ls_expected, cp.command(ls_filename));
    }

    @Test
    public void command_mkdirFileName(){
        CommandPrompt cp = new CommandPrompt();
        assertEquals(mkdir_expected, cp.command(mkdir_filename));
    }

    @Test
    public void command_wrongCommand(){
        CommandPrompt cp = new CommandPrompt();
        assertEquals(wrongCommand, cp.command("3 abc"));
    }

    @Test
    public void command_lsNoFileName(){
        CommandPrompt cp = new CommandPrompt();
        assertEquals("ls fil: ", cp.command("ls "));
    }

    @Test
    public void command_pwd(){
        CommandPrompt cp = new CommandPrompt();
        assertEquals("pwd", cp.command("pwd"));
    }

    @Test
    public void command_lsFileNameNoSpace(){
        CommandPrompt cp = new CommandPrompt();
        assertEquals(wrongCommand, cp.command("lsabc"));
    }
}
